package edu.hunau.base.support;

public enum GetShiCaiBaiKeDatas {

	SHUCAI("https://www.meishij.net/shicai/shucai_list?page=","�߲�","shucai"),
	SHUIGUO("https://www.meishij.net/shicai/shuiguo_list?page=","ˮ��","shuiguo"),
	SLDF("https://www.meishij.net/shicai/shuleidianfen_list?page=","������","sldf"),
	JUNZAO("https://www.meishij.net/shicai/junzao_list?page=","����","junzao"),
	CHUROU("https://www.meishij.net/shicai/xurou_list?page=","����","churou"),
	QINROU("https://www.meishij.net/shicai/qinrou_list?page=","����","qinrou"),
	YXBX("https://www.meishij.net/shicai/yuxiaxiebei_list?page=","��Ϻ��з","yxbx"),
	DANLEI("https://www.meishij.net/shicai/danlei_list?page=","����","danlei"),
	GULEI("https://www.meishij.net/shicai/gulei_list?page=","����","gulei"),
	GANDOU("https://www.meishij.net/shicai/gandou_list?page=","�ɶ�","gandou"),
	JGZZ("https://www.meishij.net/shicai/jianguozhongzi_list?page=","�������","jgzz"),
	;
	
	private String url;
	private String type;
	private String type_short;
	
	private GetShiCaiBaiKeDatas(String url, String type, String type_short) {
		this.url = url;
		this.type = type;
		this.type_short = type_short;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType_short() {
		return type_short;
	}

	public void setType_short(String type_short) {
		this.type_short = type_short;
	}
	
	
}
