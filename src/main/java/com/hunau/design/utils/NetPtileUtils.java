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
	 * ��Ų���ͼƬ·���ļ���
	 */
	private static List<String> caiLiaoImgUrlList = new ArrayList<String>();

	/**
	 * ��ž��岽���ͼƬ·���ļ���
	 */
	private static List<String> stepImgUrlList = new ArrayList<String>();

	/**
	 * ����url���ӻ�ȡҳ����ı���Ϣ
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
	 * ʹ��Jsoup��ȡ�ı���Ϣ
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
	 * ���ݲ���Id����ȡ��Id�µ�������Ԫ���е�����a��ǩ
	 * 
	 * @param id
	 * @return
	 */
	public static List<String> getATagsById(Document doc, String id) {
		// ��ø�Id�µ�����htmlԪ��
		Elements elements = doc.select("div#" + id);
		// ���htmlԪ���е�a��ǩ
		Elements aTags = elements.select("a");
		// System.out.println(aTags);
		// ��ȡa��ǩ�е����ӣ�����������ʽ����ȡ
		String regex = "href=\"(.+?)\"";
		String results = aTags.toString();
		// ��results��ƥ����������regex���ַ���
		List<String> lists = getSourceFromString(results, regex);
		return lists;
	}

	/**
	 * ��ȡ�ӵ�һҳ��pageSizeҳ�У�idΪid��������Ԫ���е�a��ǩ��href
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
			// ��ø�Id�µ�����htmlԪ��
			Elements elements = doc.select("div#" + id);
			// ���htmlԪ���е�a��ǩ
			Elements aTags = elements.select("a");
			// ��ȡa��ǩ�е����ӣ�����������ʽ����ȡ
			String regex = "href=\"(.+?)\"";
			String results = aTags.toString();
			// ��results��ƥ����������regex���ַ���
			List<String> lists = getSourceFromString(results, regex);
			// ������lists�е����ӣ�������hrefs��
			for (String href : lists) {
				if (!hrefs.contains(href)) {// ȥ��
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

	/**
	 * ���Ӣ����
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
	 * �Ӳ���ҳ����ȡ�Լ���Ҫ����Ϣ
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void getUsefulInfo(String url, String type_short, String type) throws Exception {
		MenuDetails menuDetails = new MenuDetails();// ����˵���ϸ��Ϣ����
		menuDetails.setType_short(type_short);
		menuDetails.setType(type);
		menuDetails.setStatus(1);
		menuDetails.setCreateTime(new Date());
		menuDetails.setClickCount(0);
		
		Document doc = getDocument(url);
		
		//��÷���չʾͼƬ
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
		
		// ��û�����Ϣ��
		Elements baseInfoDiv = doc.select("div.cp_main_info_w");

		// ��ò��׵�����
		// ������Ϣ���еĲ�������
		Elements nameDiv = baseInfoDiv.select("div.info1");
		// �Ӳ��������л�ȡ������
		String name = nameDiv.select(".title").text();
		// System.out.println(name);// ʲ�����߲�ɳ������Ȫ�󼦵�
		menuDetails.setCpname(name);

		//���׹�ģ��
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
		
		// ��ȡ���׵Ļ�����Ϣ
		String gy = baseInfoDiv.select("#tongji_gy").text();
		String nd = baseInfoDiv.select("#tongji_nd").text();
		String rsh = baseInfoDiv.select("#tongji_rsh").text();
		String kw = baseInfoDiv.select("#tongji_kw").text();
		String zbsj = baseInfoDiv.select("#tongji_zbsj").text();
		String prsj = baseInfoDiv.select("#tongji_prsj").text();
		// System.out.println(
		// "���գ�" + gy + " �Ѷȣ�" + nd + " ������" + rsh + " ��ζ��" + kw + " ׼��ʱ�䣺" +
		// zbsj + " ���ʱ�䣺" + prsj);
		menuDetails.setGy(gy);
		menuDetails.setNd(nd);
		menuDetails.setYcrs(rsh);
		menuDetails.setKw(kw);
		menuDetails.setZbsj(zbsj);
		menuDetails.setPrsj(prsj);

		// ��ò�����Ϣ��
		Elements materialsDiv = doc.select("div.materials");

		// �Ӳ�����Ϣ���л�ȡ���׵�����
		String descr = materialsDiv.select("p").html();
		menuDetails.setDescr(descr);
		// System.out.println(desc);
		// �Ӳ�����Ϣ���л�ȡ���׵����Ϻ͸���
		// ����
		Elements zhuliao = materialsDiv.select("div.zl").select("li");
		// System.out.println("���ϣ�");
		List<Materials> materials = new ArrayList<Materials>();
		int sortMain=0;
		for (Element cailiao : zhuliao) {
			sortMain++;
			Materials material = new Materials();
			material.setType(0);// ����
			String clName = cailiao.select("h4").select("a").text();// ��ò�������
			String num = cailiao.select("h4").select("span").text();// ��ò�������
			// System.out.println("��������" + clName + " ������" + num);
			// ��ò��ϵ�ͼƬ���ƣ���ͼƬ����
			String imgSrc = getImgSrc(cailiao);
			String imgName = getImgName(imgSrc);
			// ���imgUrlList��û�и�ͼƬ����ô�Ѹ�ͼƬ��ӵ�List�У�������download����
			if (!caiLiaoImgUrlList.contains(imgSrc)) {
				caiLiaoImgUrlList.add(imgSrc);
				downloadImg(imgSrc,"material");// ����ͼƬ
			}
			material.setImgName(imgName);
			material.setClmc(clName);
			material.setQuantity(num);
			material.setMenuDetails(menuDetails);
			material.setSort(sortMain);
			material.setImgSrc("http://localhost/images/materialImg/");
			materials.add(material);
		}
		// ����
		Elements fuliao = materialsDiv.select("div.fuliao").select("li");
		// System.out.println("���ϣ�");
		int sortSupport=0;
		for (Element cailiao : fuliao) {
			sortSupport++;
			Materials material = new Materials();
			material.setType(1);// ����
			String flName = cailiao.select("h4").select("a").text();
			String num = cailiao.select("span").text();
			// System.out.println("��������" + flName + " ������" + num);
			material.setClmc(flName);
			material.setQuantity(num);
			material.setSort(sortSupport);
			material.setMenuDetails(menuDetails);
			materials.add(material);
		}

		// ��������
		Elements stepsDiv = doc.select("div.measure");
		Elements steps = stepsDiv.select("div.editnew").select("div.content");
		List<Steps> stepList = new ArrayList<Steps>();
		int stepSort=0;
		for (Element element : steps) {
			stepSort++;
			Steps step = new Steps();
			String stepDesc = element.select("div.c").select("p").get(0).text();// ��ò�������
			String imgSrc = getImgSrc(element);// ��ò���ͼƬ��src
			String imgName = null;
			if (imgSrc != null) {// �ж��Ƿ���ͼƬ
				imgName = getImgName(imgSrc);// ��ò���ͼƬ��
				if (!stepImgUrlList.contains(imgSrc)) {// ����ڼ�����û�и�ͼƬ�����ӣ���ô���ȥ��������
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

		menuDetailsService.addMenuDetails(menuDetails);// �������ݿ�
		/*for (Material material : materials) {
			materialService.addData(material);// �������ݿ�
		}
		for (Step step : stepList) {
			stepService.addData(step);// �������ݿ�
		}*/
		System.out.println("success..........................");
	}

	/**
	 * ��element�л�ȡ���е�imgSrc
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
			// �����ͼƬ������
			return srcs.get(0);
		}
		return null;
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
	 * ����ͼƬ
	 * 
	 * @param imgSrc
	 *            ͼƬ��URL·��
	 */
	public static void downloadImg(String imgSrc,String action) {
		InputStream inputStream = null;
		OutputStream ouputStream = null;
		try {
			// ���ͼƬ������
			String picName = imgSrc.substring(imgSrc.lastIndexOf("/") + 1, imgSrc.length());
			// ��url�ַ���ת����URL����
			URL realUrl = new URL(imgSrc);
			// ��ȡͼƬ�ļ���������
			inputStream = realUrl.openStream();
			// �ļ���ȡ������
			byte[] content = new byte[1024];
			// �ļ������D:\\��ҵ����ļ���\\ͼƬ�ز�\\materialImg
			if("step".equals(action)){
				ouputStream = new FileOutputStream(new File("D:\\��ҵ����ļ���\\ͼƬ�ز�\\stepDetailsImg\\" + picName));
			}else if ("material".equals(action)){
				ouputStream = new FileOutputStream(new File("D:\\��ҵ����ļ���\\ͼƬ�ز�\\materialImg\\" + picName));
			}else if("headImg".equals(action)){
				ouputStream = new FileOutputStream(new File("D:\\��ҵ����ļ���\\ͼƬ�ز�\\headImg\\" + picName));
			}else if("knowledge".equals(action)){
				ouputStream = new FileOutputStream(new File("D:\\��ҵ����ļ���\\ͼƬ�ز�\\knowledge\\" + picName));
			}else if("shicaibaike".equals(action)){
				ouputStream = new FileOutputStream(new File("D:\\��ҵ����ļ���\\ͼƬ�ز�\\shicaibaike\\" + picName));
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
			// ������վ����
			String url = value.getUrl();
			String type=value.getType();
			String type_short=value.getType_short();
			// ������ȡ����ҳ��
			int pageSize = 5;
			// ��ȡÿ����ҳ�еĲ��׵�����
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
		utils.getUsefulInfo("https://www.meishij.net/zuofa/xiangguxiarennuomishaomai.html","sijiacai","˽�Ҳ�");*/
		GetDatas[] values = GetDatas.values();
		for(GetDatas value:values){
			System.out.println(value.getUrl()+"  :  "+value.getType()+"  :  "+value.getType_short());
		}
	}

}
