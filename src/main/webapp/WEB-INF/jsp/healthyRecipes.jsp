<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c"%>	
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="http://localhost/design/css/common.css">
		<link rel="stylesheet" href="http://localhost/design/css/healthyRecipes.css" />
		<link rel="stylesheet" href="http://localhost/design/css/home.css" />
		<link rel="stylesheet" href="http://localhost/design/css/page.css" />
		<link rel="stylesheet" type="text/css" href="http://www.tgcost.com/skin/default/css/font-awesome.min.css" />
		<title>健康食谱</title>
	</head>

	<body>
		<!--	头部		-->
		<div class="home_header">
			<div class="header_c">
				<a href="http://localhost/design/home.html"><img src="http://localhost/design/img/myDesignLogo.jpg" class="logo" /></a>	
				<div class="search-top">
					<form id="searchForm" method="post">
						<input id="search_input" onkeydown="suggestions()" type="text" placeholder="请输入菜谱名称" class="search-input" />
						<input id="head_search_btn" type="button" value="搜索" class="search-button" />
					</form>
					<div class="search_result">
					</div>
				</div>
				<c:choose>
				<c:when test="${user!=null }">
				<div class="login_item">
					<div class="user_info">
						<div class="user_info_w" id="userheadInfo">
							<a href="http://localhost/design/personalCenter/${user.id }.html"><img src="${user.head_img }${user.head_img_name}" class="head_img" /></a><!-- 用户个人中心 链接  -->
							<a id="usernamecolor" href="http://localhost/design/personalCenter/${user.id }.html" class="outer" style="display:inline-block;width:70px;overflow: hidden;">${user.username }</a>
							<div class="settings" style="display:none;">
								<a href="http://localhost/design/personalCenter/${user.id }.html"><span><i class="fa fa-user"></i> 个人中心</span></a>
								<a href="http://localhost/design/tuichu.html"><span><i class="fa fa-sign-out"></i> 退出登录</span></a>
								<a href="http://localhost/design/tuichu/html"><span><i class="fa fa-server"></i> 服务中心</span></a>
							</div>
						</div>
					</div>
				</div>
				</c:when>
				<c:otherwise>
				<div class="login_item">
					<div class="user_info_w" style="width:300px;">
						<a href="" class="outer user_info_a" style="display:inline-block; width:70px;left:20px;"><i class="fa fa-qq"></i>QQ登录</a>
						<a href="" class="outer user_info_a"  style="display:inline-block; width:70px;left:20px;"><i class="fa fa-wechat"></i>微信登录</a>
						<a href="http://localhost/design/login.html" class="outer user_info_a" style="display:inline-block; width:50px;left:20px;">登录</a>
						<a href="http://localhost/design/register.html" class="outer user_info_a" style="display:inline-block; width:50px;left:20px;">注册</a>
					</div>
				</div> 
				</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!--	导航		-->
		<div class="nav">
			<ul class="nav_ul">
				<li >
					<a href="http://localhost/design/home.html" class="nav_link"><strong>首页</strong></a>
				</li>
				<li class="current">
					<a href="http://localhost/design/healthyRecipes/index.html" class="nav_link"><strong>健康食谱</strong></a>
				</li>
				<li>
					<a href="" class="nav_link"><strong>个性化推荐</strong></a>
				</li>
				<li>
					<a href="http://localhost/design/knowledge/index.html" class="nav_link"><strong>健康饮食知识</strong></a>
				</li>
				<li>
					<a href="http://localhost/design/shicaibaike/index.html" class="nav_link"><strong>食材百科</strong></a>
				</li>
			</ul>
		</div>

		<!--正式内容-->
		<div class="healthyRecipesNav">
			<div class="jiachangcaipu">
				<h3 class="recipes_nav_title">家常菜谱</h3>
				<div class="jccp_content">
					<ul class="recipes_nav_ul">
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=jiachangcai">家常菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=sijiacai">私家菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=liangcai">凉菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=haixian">海鲜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=recai">热菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=tangzhou">粥汤</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=sushi">素食</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=weibolu">微波炉</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=tpdx">甜品点心</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=tpdx">糕点主食</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=ganguozhizuo">干果制作</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=lujiang">卤酱</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=shishangyinp">时尚饮品</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="jiachangcaipu">
				<h3 class="recipes_nav_title">中华菜系</h3>
				<div class="zhcx_content">
					<ul class="recipes_nav_ul_zhcx">
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=chuancai">川菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=xiangcai">湘菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=yuecai">粤菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=dongbeicai">东北菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=lucai">鲁菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=zhecai">浙菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=sucai">苏菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=qingzhencai">清真菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=mincai">闽菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=hucai">沪菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=jingcai">京菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=hubeicai">湖北菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=huicai">徽菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=yucai">豫菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=xibeicai">西北菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=yunguicai">云贵菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=jiangxicai">江西菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=shanxicai">山西菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=guangxicai">广西菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=gangtaicai">港台菜</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=qitacai">其他菜</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="meirisancan">
				<h3 class="recipes_nav_title">每日三餐</h3>
				<div class="mrsc_cotent">
					<ul class="recipes_nav_ul">
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=zaocan">早餐</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=zhongcan">午餐</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=xiawucha">下午茶</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=wancan">晚餐</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=xiaoye">夜宵</a>
						</li>

					</ul>
				</div>
			</div>
			<div class="meirisancan">
				<h3 class="recipes_nav_title">人群膳食</h3>
				<div class="mrsc_cotent">
					<ul class="recipes_nav_ul">
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=laoren">老年人</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=chanfu">产妇</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=yunfu">孕妇</a>
						</li>
						<li>
							<a href="http://localhost/design/healthyRecipes/index.html?type=baobaoyinger">宝宝食谱-婴儿食谱</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="recipes_show">
			<c:forEach items="${page.list }" var="item">
				<div class="daily_command_item">
					<a href="http://localhost/design/healthyRecipes/details/${item.id }.html" target="_blank" class="command_recipes">
						<img src="${item.img_src }${item.img_name}" class="daily_command_item_img" />
						<div class="recipes_info">
							<strong>${item.cpname }</strong>
							<span>0 评论  ${item.clickCount } 人气</span>
							<em>近乡情怯</em>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
		<!--分页模块-->
		<div class="listtyle1_page">
			<div class="listtyle1_page_w">
				<a href="http://localhost/design/healthyRecipes/index/${page.prepage }.html?type=${type}" class="next">上一页</a>
				<c:forEach  varStatus="s" begin="1" end="${page.showPageNum }">
					<a class="<c:if test="${s.count==1}">current</c:if>" title="苹果的做法大全第${s.count+page.pageNum-1}页" href="http://localhost/design/healthyRecipes/index/${s.count+page.pageNum-1}.html?type=${type}">${s.count+page.pageNum-1}</a>
				</c:forEach>
				<!-- <a title="苹果的做法大全第1页" href="" class="current">1</a>
				<a title="苹果的做法大全第2页" href="">2</a>
				<a title="苹果的做法大全第3页" href="">3</a>
				<a title="苹果的做法大全第4页" href="">4</a>
				<a title="苹果的做法大全第5页" href="">5</a> -->
				<a href="http://localhost/design/healthyRecipes/index/${page.nextpage }.html?type=${type}" class="next">下一页</a>
				<span>共 ${page.totalPage } 页</span>
			</div>
		</div>
		
		<div class="footer">
			<span class="foot_content">Hunan Agriculture University   @Author leim  Directed by ZhangYinQiong</span>
		</div>
		<script type="text/javascript" src="http://localhost/design/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="http://localhost/design/js/common.js"></script>
	</body>

</html>