package edu.hunau.base.support;

public enum GetDatas {

	/*家常菜谱*/
	JIACHANGCAI("https://www.meishij.net/chufang/diy/jiangchangcaipu/?&page=", "家常菜", "jiachangcai"),
	SIJIACAI("https://www.meishij.net/chufang/diy/sijiacai/?&page=","私家菜","sijiacai"),
	LIANGCAI("https://www.meishij.net/chufang/diy/langcaipu/?&page=","凉菜","liangcai"),
	HAIXIAN("https://www.meishij.net/chufang/diy/haixian/?&page=","海鲜","haixian"),
	RECAI("https://www.meishij.net/chufang/diy/recaipu/?&page=","热菜","recai"),
	TANGZHOU("https://www.meishij.net/chufang/diy/tangbaocaipu/?&page=","汤粥","tangzhou"),
	SUSHI("https://www.meishij.net/chufang/diy/sushi/?&page=","素食","sushi"),
	WEIBOLU("https://www.meishij.net/chufang/diy/weibolucaipu/?&page=","微波炉","weibolu"),
	TPDX("https://www.meishij.net/chufang/diy/tianpindianxin/?&page=","甜品点心","tpdx"),
	GANGUOZHIZUO("https://www.meishij.net/chufang/diy/ganguo/?&page=","干果制作","ganguozhizuo"),
	LUJIANG("https://www.meishij.net/chufang/diy/rujiangcai/?&page=","卤酱","lujiang"),
	SHISHANGYINP("https://www.meishij.net/chufang/diy/yinpin/?&page=","时尚饮品","shishangyinp"),
	/*中华菜系*/
	CHUANCAI("https://www.meishij.net/china-food/caixi/chuancai/?&page=","川菜","chuancai"),
	XIANGCAI("https://www.meishij.net/china-food/caixi/xiangcai/?&page=","湘菜","xiangcai"),
	YUECAI("https://www.meishij.net/china-food/caixi/yuecai/?&page=","粤菜","yuecai"),
	DONGBEICAI("https://www.meishij.net/china-food/caixi/dongbeicai/?&page=","东北菜","dongbeicai"),
	LUCAI("https://www.meishij.net/china-food/caixi/lucai/?&page=","鲁菜","lucai"),
	ZHECAI("https://www.meishij.net/china-food/caixi/zhecai/?&page=","浙菜","zhecai"),
	SUCAI("https://www.meishij.net/china-food/caixi/sucai/?&page=","苏菜","sucai"),
	QINGZHENCAI("https://www.meishij.net/china-food/caixi/qingzhencai/?&page=","清真菜","qingzhencai"),
	MINCAI("https://www.meishij.net/china-food/caixi/mincai/?&page=","闽菜","mincai"),
	HUCAI("https://www.meishij.net/china-food/caixi/hucai/?&page=","沪菜","hucai"),
	JINGCAI("https://www.meishij.net/china-food/caixi/jingcai/?&page=","京菜","jingcai"),
	HUBEICAI("https://www.meishij.net/china-food/caixi/hubeicai/?&page=","湖北菜","hubeicai"),
	HUICAI("https://www.meishij.net/china-food/caixi/huicai/?&page=","徽菜","huicai"),
	YUCAI("https://www.meishij.net/china-food/caixi/yucai/?&page=","豫菜","yucai"),
	XIBEICAI("https://www.meishij.net/china-food/caixi/xibeicai/?&page=","西北菜","xibeicai"),
	YUNGUICAI("https://www.meishij.net/china-food/caixi/yuguicai/?&page=","云贵菜","yunguicai"),
	JIANGXICAI("https://www.meishij.net/china-food/caixi/jiangxicai/?&page=","江西菜","jiangxicai"),
	SHANXICAI("https://www.meishij.net/china-food/caixi/shancicai/?&page=","山西菜","shanxicai"),
	GUANGXICAI("https://www.meishij.net/china-food/caixi/guangxicai/?&page=","广西菜","guangxicai"),
	GANGTAICAI("https://www.meishij.net/china-food/caixi/gangtai/?&page=","港台菜","gangtaicai"),
	QITACAI("https://www.meishij.net/china-food/caixi/other/?&page=","其他菜","qitacai"),
	
	/*每日三餐*/
	ZAOCAN("https://www.meishij.net/chufang/diy/zaocan/?&page=","早餐","zaocan"),
	WUCAN("https://www.meishij.net/chufang/diy/wucan/?&page=","中餐","zhongcan"),
	XIAWUCHA("https://www.meishij.net/chufang/diy/wancan/?&page=","下午茶","xiawucha"),
	WANCAN("https://www.meishij.net/chufang/diy/xiawucha/?&page=","晚餐","wancan"),
	XIAOYE("https://www.meishij.net/chufang/diy/yexiao/?&page=","宵夜","xiaoye"),
	
	
	
	/*人群膳食*/
	LAONIANREN("https://www.meishij.net/chufang/diy/laonian/?&page=","老人","laoren"),
	CHANFU("https://www.meishij.net/chufang/diy/chanfu/?&page=","产妇","chanfu"),
	YUNFU("https://www.meishij.net/chufang/diy/yunfu/?&page=","孕妇","yunfu"),
	BAOBAOYINER("https://www.meishij.net/chufang/diy/baobaocaipu/?&page=","宝宝婴儿","baobaoyinger"),
	
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
