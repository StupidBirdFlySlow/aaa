package edu.hunau.base.support;

public enum GetDietKnowledgeDatas {

	MEISHIDIANGU("https://www.meishij.net/health.php?cid=31&sortby=update&page=","美食典故","meishidiangu","了解那些美食的历史典故"),
	CHAWENHUA("https://www.meishij.net/health.php?cid=32&sortby=update&page=","茶文化","chawenhua","茶说：我就是一杯水，你看到的都是你的想象"),
	WAIGUOYSWH("https://www.meishij.net/health.php?cid=33&sortby=update&page=","外国饮食文化","waiguoyswh","看国外丰富多彩的饮食文化"),
	JIUWENHUA("https://www.meishij.net/health.php?cid=34&sortby=update&page=","酒文化","jiuwenhua","喝酒一定要了解的那些常识"),
	ZHONGXICANLIYI("https://www.meishij.net/health.php?cid=36&sortby=update&page=","中西餐礼仪","zhongxicanliyi","应该学会的那些中西餐礼仪"),
	YSXCS("https://www.meishij.net/health.php?cid=19&sortby=update&page=","饮食小常识","ysxcs","帮助你了解更多的饮食小常识"),
	YSJJ("https://www.meishij.net/health.php?cid=25&sortby=update&page=","饮食禁忌","ysjj","生活中需要知道的那些饮食间的禁忌"),
	YSMF("https://www.meishij.net/health.php?cid=347&sortby=update&page=","养生妙方","ysmf","掌握养生妙方，饮食健康事半功倍"),
	ZYBJ("https://www.meishij.net/health.php?cid=346&sortby=update&page=","中医保健","zybj","传承5000年历史，看国粹中医养生"),
	MRSS("https://www.meishij.net/health.php?cid=20&sortby=update&page=","美容瘦身","mrss","这个不就是变成美女的最好办法吗！"),
	MYJKYS("https://www.meishij.net/health.php?cid=21&sortby=update&page=","母婴健康饮食","myjkys","解决妈妈烦恼，宝宝健康成长")
	;
	private String url;
	private String type;
	private String type_short;
	private String tips;

	private GetDietKnowledgeDatas(String url, String type, String type_short, String tips) {
		this.url = url;
		this.type = type;
		this.type_short = type_short;
		this.tips = tips;
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

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

}
