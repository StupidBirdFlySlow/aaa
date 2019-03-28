package edu.hunau.base.support;

public enum GetDietKnowledgeDatas {

	MEISHIDIANGU("https://www.meishij.net/health.php?cid=31&sortby=update&page=","��ʳ���","meishidiangu","�˽���Щ��ʳ����ʷ���"),
	CHAWENHUA("https://www.meishij.net/health.php?cid=32&sortby=update&page=","���Ļ�","chawenhua","��˵���Ҿ���һ��ˮ���㿴���Ķ����������"),
	WAIGUOYSWH("https://www.meishij.net/health.php?cid=33&sortby=update&page=","�����ʳ�Ļ�","waiguoyswh","������ḻ��ʵ���ʳ�Ļ�"),
	JIUWENHUA("https://www.meishij.net/health.php?cid=34&sortby=update&page=","���Ļ�","jiuwenhua","�Ⱦ�һ��Ҫ�˽����Щ��ʶ"),
	ZHONGXICANLIYI("https://www.meishij.net/health.php?cid=36&sortby=update&page=","����������","zhongxicanliyi","Ӧ��ѧ�����Щ����������"),
	YSXCS("https://www.meishij.net/health.php?cid=19&sortby=update&page=","��ʳС��ʶ","ysxcs","�������˽�������ʳС��ʶ"),
	YSJJ("https://www.meishij.net/health.php?cid=25&sortby=update&page=","��ʳ����","ysjj","��������Ҫ֪������Щ��ʳ��Ľ���"),
	YSMF("https://www.meishij.net/health.php?cid=347&sortby=update&page=","�����","ysmf","�������������ʳ�����°빦��"),
	ZYBJ("https://www.meishij.net/health.php?cid=346&sortby=update&page=","��ҽ����","zybj","����5000����ʷ����������ҽ����"),
	MRSS("https://www.meishij.net/health.php?cid=20&sortby=update&page=","��������","mrss","��������Ǳ����Ů����ð취��"),
	MYJKYS("https://www.meishij.net/health.php?cid=21&sortby=update&page=","ĸӤ������ʳ","myjkys","������跳�գ����������ɳ�")
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
