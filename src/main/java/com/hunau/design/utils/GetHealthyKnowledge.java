package com.hunau.design.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.hunau.base.model.DietKnowledge;
import edu.hunau.base.service.DietKnowledgeService;
import edu.hunau.base.support.GetDietKnowledgeDatas;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class GetHealthyKnowledge {

	@Autowired
	@Qualifier("dietKnowledgeService")
	private DietKnowledgeService dietKnowledgeService;

	@Test
	public void getDatas() throws IOException {
		/*
		 * String
		 * url="https://www.meishij.net/health.php?cid=31&sortby=update&page=2";
		 * getUsefulInfo(url,"","","");
		 */
		GetDietKnowledgeDatas[] values = GetDietKnowledgeDatas.values();
		for (GetDietKnowledgeDatas value : values) {
			int page=4;
			for(int i=0;i<page;i++){
				getUsefulInfo(value.getUrl()+(i+1),value.getType(),value.getType_short(),value.getTips());
			}
		}

	}

	/**
	 * 向链接发起请求，获得页面文档信息
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public Document getConnectWithUrl(String url) throws IOException {
		Document document = Jsoup.connect(url).timeout(10000).get();// 设置等待响应时间
		return document;
	}

	public void getUsefulInfo(String url, String type, String type_short, String tips) throws IOException {
		Document document = getConnectWithUrl(url);
		Elements contentDiv = document.select("div#listtyle1_list");
		Elements contents = contentDiv.select("div.listtyle1");
		System.out.println(contents.size());
		for (Element content : contents) {
			DietKnowledge entity = new DietKnowledge();
			entity.setType(type);
			entity.setType_short(type_short);
			entity.setTips(tips);
			entity.setClickCount(0);
			entity.setStatus(1);

			Elements aTagElements = content.select("div.img").select("a");
			String aTags = aTagElements.toString();
			String href = getHrefFromATags(aTags);// 从模块中获得详细信息的链接
			// System.out.println(href);
			Elements imgElements = aTagElements.select("img");
			// System.out.println(imgElements);
			// 从ImgElements中获取图片的路径
			String src = getSrcFromImgTags(imgElements.toString());
			System.out.println(src);
			String imgName = getImgName(src);// 图片名称 imgName
			entity.setImg_name(imgName);
			String imgSrc = "D:\\毕业设计文件夹\\图片素材\\knowledege\\";// 图片存放路径 imgSrc

			entity.setImg_url(imgSrc);
			// 下载图片
			NetPtileUtils.downloadImg(src, "knowledge");
			// 获取饮食知识的名字
			Elements info = content.select("div.info1");
			String name = info.select("strong").select("a").html();

			entity.setName(name);
			String knowContent = getContent(href);
			entity.setContent(knowContent);

			dietKnowledgeService.addDietKnowledge(entity);// 存库
		}
	}

	/**
	 * 从a标签中获取href链接
	 * 
	 * @param aTags
	 * @return
	 */
	public String getHrefFromATags(String aTags) {
		String regex = "href=\"(.+?)\"";
		List<String> hrefs = getSourceFromString(aTags, regex);
		return hrefs.get(0);
	}

	/**
	 * 从img标签中获取src链接
	 * 
	 * @param imgTags
	 * @return
	 */
	public String getSrcFromImgTags(String imgTags) {
		String regex = "src=\"(.+?)\"";
		List<String> srcs = getSourceFromString(imgTags, regex);
		return srcs.get(0);
	}

	/**
	 * 从ImgSrc中获取ImgName
	 * 
	 * @param imgSrc
	 * @return
	 */
	public static String getImgName(String imgSrc) {
		String imgName = imgSrc.substring(imgSrc.lastIndexOf("/") + 1, imgSrc.length());
		return imgName;
	}

	/**
	 * 根据正则表达式，获取指定的内容，一般是链接
	 * 
	 * @param results
	 * @param regex
	 * @return
	 */
	public static List<String> getSourceFromString(String results, String regex) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(results);
		while (matcher.find()) {
			list.add(matcher.group(1));
		}
		return list;
	}

	public String getContent(String url) throws IOException {
		Document document = getConnectWithUrl(url);
		Elements select = document.select("div.measure").select("div.edit");
		return select.toString();
	}

}
