package edu.hunau.base.support;

public enum GetDatas {

	/*�ҳ�����*/
	JIACHANGCAI("https://www.meishij.net/chufang/diy/jiangchangcaipu/?&page=", "�ҳ���", "jiachangcai"),
	SIJIACAI("https://www.meishij.net/chufang/diy/sijiacai/?&page=","˽�Ҳ�","sijiacai"),
	LIANGCAI("https://www.meishij.net/chufang/diy/langcaipu/?&page=","����","liangcai"),
	HAIXIAN("https://www.meishij.net/chufang/diy/haixian/?&page=","����","haixian"),
	RECAI("https://www.meishij.net/chufang/diy/recaipu/?&page=","�Ȳ�","recai"),
	TANGZHOU("https://www.meishij.net/chufang/diy/tangbaocaipu/?&page=","����","tangzhou"),
	SUSHI("https://www.meishij.net/chufang/diy/sushi/?&page=","��ʳ","sushi"),
	WEIBOLU("https://www.meishij.net/chufang/diy/weibolucaipu/?&page=","΢��¯","weibolu"),
	TPDX("https://www.meishij.net/chufang/diy/tianpindianxin/?&page=","��Ʒ����","tpdx"),
	GANGUOZHIZUO("https://www.meishij.net/chufang/diy/ganguo/?&page=","�ɹ�����","ganguozhizuo"),
	LUJIANG("https://www.meishij.net/chufang/diy/rujiangcai/?&page=","±��","lujiang"),
	SHISHANGYINP("https://www.meishij.net/chufang/diy/yinpin/?&page=","ʱ����Ʒ","shishangyinp"),
	/*�л���ϵ*/
	CHUANCAI("https://www.meishij.net/china-food/caixi/chuancai/?&page=","����","chuancai"),
	XIANGCAI("https://www.meishij.net/china-food/caixi/xiangcai/?&page=","���","xiangcai"),
	YUECAI("https://www.meishij.net/china-food/caixi/yuecai/?&page=","����","yuecai"),
	DONGBEICAI("https://www.meishij.net/china-food/caixi/dongbeicai/?&page=","������","dongbeicai"),
	LUCAI("https://www.meishij.net/china-food/caixi/lucai/?&page=","³��","lucai"),
	ZHECAI("https://www.meishij.net/china-food/caixi/zhecai/?&page=","���","zhecai"),
	SUCAI("https://www.meishij.net/china-food/caixi/sucai/?&page=","�ղ�","sucai"),
	QINGZHENCAI("https://www.meishij.net/china-food/caixi/qingzhencai/?&page=","�����","qingzhencai"),
	MINCAI("https://www.meishij.net/china-food/caixi/mincai/?&page=","����","mincai"),
	HUCAI("https://www.meishij.net/china-food/caixi/hucai/?&page=","����","hucai"),
	JINGCAI("https://www.meishij.net/china-food/caixi/jingcai/?&page=","����","jingcai"),
	HUBEICAI("https://www.meishij.net/china-food/caixi/hubeicai/?&page=","������","hubeicai"),
	HUICAI("https://www.meishij.net/china-food/caixi/huicai/?&page=","�ղ�","huicai"),
	YUCAI("https://www.meishij.net/china-food/caixi/yucai/?&page=","ԥ��","yucai"),
	XIBEICAI("https://www.meishij.net/china-food/caixi/xibeicai/?&page=","������","xibeicai"),
	YUNGUICAI("https://www.meishij.net/china-food/caixi/yuguicai/?&page=","�ƹ��","yunguicai"),
	JIANGXICAI("https://www.meishij.net/china-food/caixi/jiangxicai/?&page=","������","jiangxicai"),
	SHANXICAI("https://www.meishij.net/china-food/caixi/shancicai/?&page=","ɽ����","shanxicai"),
	GUANGXICAI("https://www.meishij.net/china-food/caixi/guangxicai/?&page=","������","guangxicai"),
	GANGTAICAI("https://www.meishij.net/china-food/caixi/gangtai/?&page=","��̨��","gangtaicai"),
	QITACAI("https://www.meishij.net/china-food/caixi/other/?&page=","������","qitacai"),
	
	/*ÿ������*/
	ZAOCAN("https://www.meishij.net/chufang/diy/zaocan/?&page=","���","zaocan"),
	WUCAN("https://www.meishij.net/chufang/diy/wucan/?&page=","�в�","zhongcan"),
	XIAWUCHA("https://www.meishij.net/chufang/diy/wancan/?&page=","�����","xiawucha"),
	WANCAN("https://www.meishij.net/chufang/diy/xiawucha/?&page=","���","wancan"),
	XIAOYE("https://www.meishij.net/chufang/diy/yexiao/?&page=","��ҹ","xiaoye"),
	
	
	
	/*��Ⱥ��ʳ*/
	LAONIANREN("https://www.meishij.net/chufang/diy/laonian/?&page=","����","laoren"),
	CHANFU("https://www.meishij.net/chufang/diy/chanfu/?&page=","����","chanfu"),
	YUNFU("https://www.meishij.net/chufang/diy/yunfu/?&page=","�и�","yunfu"),
	BAOBAOYINER("https://www.meishij.net/chufang/diy/baobaocaipu/?&page=","����Ӥ��","baobaoyinger"),
	
	;

	private String url;
	private String type;
	private String type_short;

	private GetDatas(String url, String type, String type_short) {
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
