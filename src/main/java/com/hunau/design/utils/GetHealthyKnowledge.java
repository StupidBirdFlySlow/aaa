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
	 * �����ӷ������󣬻��ҳ���ĵ���Ϣ
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public Document getConnectWithUrl(String url) throws IOException {
		Document document = Jsoup.connect(url).timeout(10000).get();// ���õȴ���Ӧʱ��
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
			String href = getHrefFromATags(aTags);// ��ģ���л����ϸ��Ϣ������
			// System.out.println(href);
			Elements imgElements = aTagElements.select("img");
			// System.out.println(imgElements);
			// ��ImgElements�л�ȡͼƬ��·��
			String src = getSrcFromImgTags(imgElements.toString());
			System.out.println(src);
			String imgName = getImgName(src);// ͼƬ���� imgName
			entity.setImg_name(imgName);
			String imgSrc = "D:\\��ҵ����ļ���\\ͼƬ�ز�\\knowledege\\";// ͼƬ���·�� imgSrc

			entity.setImg_url(imgSrc);
			// ����ͼƬ
			NetPtileUtils.downloadImg(src, "knowledge");
			// ��ȡ��ʳ֪ʶ������
			Elements info = content.select("div.info1");
			String name = info.select("strong").select("a").html();

			entity.setName(name);
			String knowContent = getContent(href);
			entity.setContent(knowContent);

			dietKnowledgeService.addDietKnowledge(entity);// ���
		}
	}

	/**
	 * ��a��ǩ�л�ȡhref����
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
	 * ��img��ǩ�л�ȡsrc����
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
	 * ��ImgSrc�л�ȡImgName
	 * 
	 * @param imgSrc
	 * @return
	 */
	public static String getImgName(String imgSrc) {
		String imgName = imgSrc.substring(imgSrc.lastIndexOf("/") + 1, imgSrc.length());
		return imgName;
	}

	/**
	 * ����������ʽ����ȡָ�������ݣ�һ��������
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
