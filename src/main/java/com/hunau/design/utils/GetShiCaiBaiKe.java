package com.hunau.design.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import edu.hunau.base.model.ShiCaiBaiKe;
import edu.hunau.base.service.ShiCaiBaiKeService;
import edu.hunau.base.support.GetShiCaiBaiKeDatas;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class GetShiCaiBaiKe {

	@Autowired
	@Qualifier("shiCaiBaiKeService")
	private ShiCaiBaiKeService shiCaiBaiKeService;

	@Test
	public void getShiCaiBaike() throws IOException {
		GetShiCaiBaiKeDatas[] values = GetShiCaiBaiKeDatas.values();
		for (GetShiCaiBaiKeDatas value : values) {
			int page=10;
			for(int i=0;i<page;i++){
				getUsefulInfo(value.getUrl()+(i+1), value.getType(), value.getType_short());
			}
		}
		
	}

	public void getUsefulInfo(String url, String type, String type_short) throws IOException {
		Document document = getConnectWithUrl(url);
		Elements contentDiv = document.select("div#listtyle1_list");
		Elements contents = contentDiv.select("div.listtyle1");
		for (Element content : contents) {
			ShiCaiBaiKe entity = new ShiCaiBaiKe();
			entity.setType(type);
			entity.setType_short(type_short);
			entity.setClickCount(0);
			entity.setStatus(1);

			Elements aTagElements = content.select("div.img").select("a");
			String aTags = aTagElements.toString();
			String href = getHrefFromATags(aTags);// 从模块中获得详细信息的链接
			System.out.println(href);
			// System.out.println(href);
			Elements imgElements = aTagElements.select("img");
			// System.out.println(imgElements);
			// 从ImgElements中获取图片的路径
			String src = getSrcFromImgTags(imgElements.toString());

			String imgName = getImgName(src);// 图片名称 imgName
			entity.setImg_name(imgName);

			String imgSrc = "images\\shicaibaike\\";// 图片存放路径 imgSrc
			entity.setImg_url(imgSrc);

			// 下载图片
			NetPtileUtils.downloadImg(src, "shicaibaike");
			// 获取饮食知识的名字
			Elements info = content.select("div.info1");
			String name = info.select("h3").select("a").html();
			entity.setName(name);

			String desc = info.select("div.d1").select("span").html();
			entity.setDescr(desc);

			entity = getContent(entity, href);

			shiCaiBaiKeService.addShiCaiBaiKe(entity);
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

	public ShiCaiBaiKe getContent(ShiCaiBaiKe entity, String url) throws IOException {
		String href = encoder(url);
		Document document = getConnectWithUrl(href);
		System.out.println("----------------------------------");
		String html = document.select("div.sc_header").select("div.sc_header_con1").select(".p2").toString();
		entity.setBaseInfo(html);
		Elements select = document.select("div#sccon_right").select("div.sccon_right_w");
		entity.setContent(select.toString());
		return entity;
	}

	public String encoder(String url) throws UnsupportedEncodingException {
		String url1 = url.substring(0, url.lastIndexOf("/") + 1);
		String string = url.substring(url.lastIndexOf("/") + 1, url.length());
		String url2 = URLEncoder.encode(string, "UTF-8");
		url = url1 + url2;
		return url;
	}

}
