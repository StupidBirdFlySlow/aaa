<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="http://localhost/design/css/common.css">
		<link rel="stylesheet" href="http://localhost/design/css/home.css" />
		<link rel="stylesheet" href="http://localhost/design/css/healthyRecipes.css" />
		<link rel="stylesheet" href="http://localhost/design/css/page.css" />
		<link rel="stylesheet" type="text/css" href="http://www.tgcost.com/skin/default/css/font-awesome.min.css" />
		<title>健康饮食知识</title>
	</head>

	<body>
		<!--	头部		-->
		<div class="home_header">
			<div class="header_c">
				<a href=""><img src="http://localhost/design/img/myDesignLogo.jpg" class="logo" /></a>
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
							<a href="http://localhost/design/personalCenter/${user.id }.html"><img src="http://localhost/design/img/myDesignLogo.jpg" class="head_img" /></a><!-- 用户个人中心 链接  -->
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
				<li>
					<a href="http://localhost/design/home.html" class="nav_link"><strong>首页</strong></a>
				</li>
				<li>
					<a href="http://localhost/design/healthyRecipes/index.html" class="nav_link"><strong>健康食谱</strong></a>
				</li>
				<li>
					<a href="" class="nav_link"><strong>个性化推荐</strong></a>
				</li>
				<li class="current">
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
				<h3 class="recipes_nav_title">饮食文化</h3>
				<div class="jccp_content">
					<ul class="recipes_nav_ul">
						<li>
							<a href="http://localhost/design/knowledge/index.html?type=meishidiangu">美食典故</a>
						</li>
						<li>
							<a href="http://localhost/design/knowledgee/index.html?type=chawenhua">茶文化</a>
						</li>
						<li>
							<a href="http://localhost/design/knowledge/index.html?type=waiguoyswh">外国饮食文化</a>
						</li>
						<li>
							<a href="http://localhost/design/knowledge/index.html?type=jiuwenhua">酒文化</a>
						</li>
						<li>
							<a href="http://localhost/design/knowledge/index.html?type=zhongxicanliyi">中西餐礼仪</a>
						</li>
					</ul>
				</div>

			</div>
			<div class="jiachangcaipu">
				<h3 class="recipes_nav_title">健康指南</h3>
				<div class="jccp_content">
					<ul class="recipes_nav_ul">
						<li>
							<a href="http://localhost/design/knowledge/index.html?type=ysxcs">饮食小常识</a>
						</li>
						<li>
							<a href="http://localhost/design/knowledge/index.html?type=ysjj">饮食禁忌</a>
						</li>
						<li>
							<a href="http://localhost/design/knowledge/index.html?type=ysmf">养生秘方</a>
						</li>
						<li>
							<a href="http://localhost/design/knowledge/index.html?type=zybj">中医保健</a>
						</li>
						<li>
							<a href="http://localhost/design/knowledge/index.html?type=mrss">美容瘦身</a>
						</li>
						<li>
							<a href="http://localhost/design/knowledge/index.html?type=myjkys">母婴健康饮食</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!--正式内容-->
		<div class="health_knowledge_content">
			<c:forEach items="${page.list}" var="item">
				<div class="daily_command_item">
					<a href="http://localhost/design/knowledge/details/${item.id }.html" target="_blank" class="command_recipes">
						<img src="${item.img_url }${item.img_name}" class="daily_command_item_img" />
						<div class="recipes_info">
							<strong>${item.name }</strong>
							<span>0 评论  ${item.clickCount }人气</span>
							<em>近乡情怯</em>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
		<!--分页-->
		<div class="listtyle1_page">
				<div class="listtyle1_page_w">
					<a href="http://localhost/design/knowledge/index/${page.prepage }.html?type=${type}" class="next">上一页</a>
					<c:forEach  varStatus="s" begin="1" end="${page.showPageNum }">
						<a class="<c:if test="${s.count==1}">current</c:if>" title="苹果的做法大全第${s.count+page.pageNum-1}页" href="http://localhost/design/knowledge/index/${s.count+page.pageNum-1}.html?type=${type}">${s.count+page.pageNum-1}</a>
					</c:forEach>
					<a href="http://localhost/design/knowledge/index/${page.nextpage }.html?type=${type}" class="next">下一页</a>
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