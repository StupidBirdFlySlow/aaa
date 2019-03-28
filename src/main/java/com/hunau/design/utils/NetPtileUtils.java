package com.hunau.design.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
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

import edu.hunau.base.model.Materials;
import edu.hunau.base.model.MenuDetails;
import edu.hunau.base.model.MenuFunction;
import edu.hunau.base.model.Steps;
import edu.hunau.base.service.MenuDetailsService;
import edu.hunau.base.support.GetDatas;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class NetPtileUtils {
	
	@Autowired
	@Qualifier("menuDetailsService")
	private MenuDetailsService menuDetailsService;

	/**
	 * 存放材料图片路径的集合
	 */
	private static List<String> caiLiaoImgUrlList = new ArrayList<String>();

	/**
	 * 存放具体步骤的图片路径的集合
	 */
	private static List<String> stepImgUrlList = new ArrayList<String>();

	/**
	 * 根据url链接获取页面的文本信息
	 * 
	 * @param url
	 * @return
	 */
	public String getHtml(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 使用Jsoup获取文本信息
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Document getDocument(String url) throws Exception {
		Document document = Jsoup.connect(url).timeout(10000).get();
		return document;
	}

	/**
	 * 根据参数Id，获取该Id下的所有子元素中的所有a标签
	 * 
	 * @param id
	 * @return
	 */
	public static List<String> getATagsById(Document doc, String id) {
		// 获得该Id下的所有html元素
		Elements elements = doc.select("div#" + id);
		// 获得html元素中的a标签
		Elements aTags = elements.select("a");
		// System.out.println(aTags);
		// 获取a标签中的链接，根据正则表达式来获取
		String regex = "href=\"(.+?)\"";
		String results = aTags.toString();
		// 从results中匹配所有满足regex的字符串
		List<String> lists = getSourceFromString(results, regex);
		return lists;
	}

	/**
	 * 获取从第一页到pageSize页中，id为id的所有子元素中的a标签的href
	 * 
	 * @param url
	 * @param pageSize
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<String> getATagsById(String url, int pageSize, String id) throws Exception {
		List<String> hrefs = new ArrayList<String>();
		for (int i = 1; i <= pageSize; i++) {
			url = url + i;
			Document doc = getDocument(url);
			// 获得该Id下的所有html元素
			Elements elements = doc.select("div#" + id);
			// 获得html元素中的a标签
			Elements aTags = elements.select("a");
			// 获取a标签中的链接，根据正则表达式来获取
			String regex = "href=\"(.+?)\"";
			String results = aTags.toString();
			// 从results中匹配所有满足regex的字符串
			List<String> lists = getSourceFromString(results, regex);
			// 迭代出lists中的链接，并存入hrefs中
			for (String href : lists) {
				if (!hrefs.contains(href)) {// 去重
					hrefs.add(href);
				}
			}
		}
		return hrefs;
	}

	public List<String> getHrefsByPageNum(String url, int pageNum, String id) throws Exception {
		url = url + pageNum;
		Document doc = getDocument(url);
		Elements elements = doc.select("div#" + id);
		Elements aTags = elements.select("a");
		String regex = "href=\"(.+?)\"";
		String results = aTags.toString();
		List<String> lists = getSourceFromString(results, regex);
		return lists;
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

	/**
	 * 获得英文名
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String getUrlShort(String url) throws Exception {
		String urlShort = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		System.out.println(urlShort);
		return urlShort;
	}

	/**
	 * 从菜谱页面提取自己需要的信息
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void getUsefulInfo(String url, String type_short, String type) throws Exception {
		MenuDetails menuDetails = new MenuDetails();// 定义菜单详细信息对象
		menuDetails.setType_short(type_short);
		menuDetails.setType(type);
		menuDetails.setStatus(1);
		menuDetails.setCreateTime(new Date());
		menuDetails.setClickCount(0);
		
		Document doc = getDocument(url);
		
		//获得封面展示图片
		Elements headImgDidv=doc.select("div.cp_headerimg_w");
		Elements headImgs = headImgDidv.select("img");
		if(headImgs.size()==0){
			return;
		}
		Element haedImg = headImgs.get(0);
		String headImgSrc = getImgSrc(haedImg);
		String headImgName=getImgName(headImgSrc);
		downloadImg(headImgSrc, "headImg");
		menuDetails.setImg_name(headImgName);
		menuDetails.setImg_src("http:///localhost//images//headImg//");
		
		// 获得基本信息块
		Elements baseInfoDiv = doc.select("div.cp_main_info_w");

		// 获得菜谱的名称
		// 基本信息块中的菜谱名块
		Elements nameDiv = baseInfoDiv.select("div.info1");
		// 从菜谱名块中获取菜谱名
		String name = nameDiv.select(".title").text();
		// System.out.println(name);// 什锦热蔬菜沙拉配温泉煮鸡蛋
		menuDetails.setCpname(name);

		//菜谱功模块
		Elements funcs = nameDiv.select("dl.yj_tags").select("dt");
		if(funcs.size()!=0){
			int sortFunc=0;
			String cpFunc=funcs.get(0).select("a").html();
			menuDetails.setFunc(cpFunc);
			List<MenuFunction> funcList=new ArrayList<MenuFunction>();
			for(Element func:funcs){
				sortFunc++;
				String html = func.select("a").html();
				MenuFunction menuFuncs=new MenuFunction();
				menuFuncs.setFunc(html);
				menuFuncs.setMenuDetails(menuDetails);
				menuFuncs.setSort(sortFunc);
				funcList.add(menuFuncs);
			}
			menuDetails.setFuncs(funcList);
		}
		
		// 获取菜谱的基本信息
		String gy = baseInfoDiv.select("#tongji_gy").text();
		String nd = baseInfoDiv.select("#tongji_nd").text();
		String rsh = baseInfoDiv.select("#tongji_rsh").text();
		String kw = baseInfoDiv.select("#tongji_kw").text();
		String zbsj = baseInfoDiv.select("#tongji_zbsj").text();
		String prsj = baseInfoDiv.select("#tongji_prsj").text();
		// System.out.println(
		// "工艺：" + gy + " 难度：" + nd + " 人数：" + rsh + " 口味：" + kw + " 准备时间：" +
		// zbsj + " 烹饪时间：" + prsj);
		menuDetails.setGy(gy);
		menuDetails.setNd(nd);
		menuDetails.setYcrs(rsh);
		menuDetails.setKw(kw);
		menuDetails.setZbsj(zbsj);
		menuDetails.setPrsj(prsj);

		// 获得材料信息块
		Elements materialsDiv = doc.select("div.materials");

		// 从材料信息块中获取菜谱的描述
		String descr = materialsDiv.select("p").html();
		menuDetails.setDescr(descr);
		// System.out.println(desc);
		// 从材料信息块中获取菜谱的主料和辅料
		// 主料
		Elements zhuliao = materialsDiv.select("div.zl").select("li");
		// System.out.println("主料：");
		List<Materials> materials = new ArrayList<Materials>();
		int sortMain=0;
		for (Element cailiao : zhuliao) {
			sortMain++;
			Materials material = new Materials();
			material.setType(0);// 主料
			String clName = cailiao.select("h4").select("a").text();// 获得材料名称
			String num = cailiao.select("h4").select("span").text();// 获得材料数量
			// System.out.println("材料名：" + clName + " 分量：" + num);
			// 获得材料的图片名称，和图片链接
			String imgSrc = getImgSrc(cailiao);
			String imgName = getImgName(imgSrc);
			// 如果imgUrlList中没有该图片，那么把该图片添加到List中，并调用download方法
			if (!caiLiaoImgUrlList.contains(imgSrc)) {
				caiLiaoImgUrlList.add(imgSrc);
				downloadImg(imgSrc,"material");// 下载图片
			}
			material.setImgName(imgName);
			material.setClmc(clName);
			material.setQuantity(num);
			material.setMenuDetails(menuDetails);
			material.setSort(sortMain);
			material.setImgSrc("http://localhost/images/materialImg/");
			materials.add(material);
		}
		// 辅料
		Elements fuliao = materialsDiv.select("div.fuliao").select("li");
		// System.out.println("辅料：");
		int sortSupport=0;
		for (Element cailiao : fuliao) {
			sortSupport++;
			Materials material = new Materials();
			material.setType(1);// 辅料
			String flName = cailiao.select("h4").select("a").text();
			String num = cailiao.select("span").text();
			// System.out.println("材料名：" + flName + " 分量：" + num);
			material.setClmc(flName);
			material.setQuantity(num);
			material.setSort(sortSupport);
			material.setMenuDetails(menuDetails);
			materials.add(material);
		}

		// 具体做法
		Elements stepsDiv = doc.select("div.measure");
		Elements steps = stepsDiv.select("div.editnew").select("div.content");
		List<Steps> stepList = new ArrayList<Steps>();
		int stepSort=0;
		for (Element element : steps) {
			stepSort++;
			Steps step = new Steps();
			String stepDesc = element.select("div.c").select("p").get(0).text();// 获得步骤描述
			String imgSrc = getImgSrc(element);// 获得步骤图片的src
			String imgName = null;
			if (imgSrc != null) {// 判断是否有图片
				imgName = getImgName(imgSrc);// 获得步骤图片名
				if (!stepImgUrlList.contains(imgSrc)) {// 如果在集合中没有该图片的链接，那么存进去，并下载
					stepImgUrlList.add(imgSrc);
					downloadImg(imgSrc,"step");
				}
			}
			// System.out.println(stepDesc);
			// System.out.println(imgName + " : " + imgSrc);
			step.setMenuDetails(menuDetails);
			step.setStep_desc(stepDesc);
			step.setStep_img_name(imgName);
			step.setStep_img("http://localhost/images/materialImg/");
			step.setStep_sort(stepSort);
			stepList.add(step);
		}

		menuDetails.setSteps(stepList);
		menuDetails.setMaterials(materials);

		menuDetailsService.addMenuDetails(menuDetails);// 存入数据库
		/*for (Material material : materials) {
			materialService.addData(material);// 存入数据库
		}
		for (Step step : stepList) {
			stepService.addData(step);// 存入数据库
		}*/
		System.out.println("success..........................");
	}

	/**
	 * 从element中获取其中的imgSrc
	 * 
	 * @param element
	 * @return
	 */
	public static String getImgSrc(Element element) {
		Elements img = element.select("img");
		/* System.out.println(img); */
		String imgStr = img.toString();
		String regex = "src=\"(.+?)\"";
		List<String> srcs = getSourceFromString(imgStr, regex);
		if (srcs.size() == 1) {
			// 获得了图片的链接
			return srcs.get(0);
		}
		return null;
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
	 * 下载图片
	 * 
	 * @param imgSrc
	 *            图片的URL路径
	 */
	public static void downloadImg(String imgSrc,String action) {
		InputStream inputStream = null;
		OutputStream ouputStream = null;
		try {
			// 获得图片的名称
			String picName = imgSrc.substring(imgSrc.lastIndexOf("/") + 1, imgSrc.length());
			// 把url字符串转换成URL对象
			URL realUrl = new URL(imgSrc);
			// 获取图片文件的输入流
			inputStream = realUrl.openStream();
			// 文件读取缓存区
			byte[] content = new byte[1024];
			// 文件输出流D:\\毕业设计文件夹\\图片素材\\materialImg
			if("step".equals(action)){
				ouputStream = new FileOutputStream(new File("D:\\毕业设计文件夹\\图片素材\\stepDetailsImg\\" + picName));
			}else if ("material".equals(action)){
				ouputStream = new FileOutputStream(new File("D:\\毕业设计文件夹\\图片素材\\materialImg\\" + picName));
			}else if("headImg".equals(action)){
				ouputStream = new FileOutputStream(new File("D:\\毕业设计文件夹\\图片素材\\headImg\\" + picName));
			}else if("knowledge".equals(action)){
				ouputStream = new FileOutputStream(new File("D:\\毕业设计文件夹\\图片素材\\knowledge\\" + picName));
			}else if("shicaibaike".equals(action)){
				ouputStream = new FileOutputStream(new File("D:\\毕业设计文件夹\\图片素材\\shicaibaike\\" + picName));
			}

			int length = 0;
			while ((length = inputStream.read(content)) != -1) {
				ouputStream.write(content, 0, length);
				ouputStream.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ouputStream != null) {
					ouputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	@Test
	public void getDatas() throws Exception {
		GetDatas[] values = GetDatas.values();
		for(GetDatas value:values){
			// 定义网站连接
			String url = value.getUrl();
			String type=value.getType();
			String type_short=value.getType_short();
			// 定义爬取得网页数
			int pageSize = 5;
			// 获取每个网页中的菜谱的链接
			for (int i = 1; i <= pageSize; i++) {
				List<String> hrefs = getHrefsByPageNum(url, i, "listtyle1_list");
				for (String href : hrefs) {
					System.out.println(href + "   :    " + i);
					getUsefulInfo(href,type_short,type);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		/*NetPtileUtils utils = new NetPtileUtils();
		utils.getUrlShort("http://www.meishij.net/zuofa/heidouyachaorousi_2.html");
		utils.getUsefulInfo("https://www.meishij.net/zuofa/xiangguxiarennuomishaomai.html","sijiacai","私家菜");*/
		GetDatas[] values = GetDatas.values();
		for(GetDatas value:values){
			System.out.println(value.getUrl()+"  :  "+value.getType()+"  :  "+value.getType_short());
		}
	}

}
