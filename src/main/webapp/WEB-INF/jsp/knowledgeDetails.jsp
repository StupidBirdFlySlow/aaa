<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>健康饮食知识详细</title>
		<link rel="stylesheet" href="http://localhost/design/css/common.css">
		<link rel="stylesheet" href="http://localhost/design/css/home.css" />
		<link rel="stylesheet" href="http://localhost/design/css/knowledgeDetails.css" />
		<link rel="stylesheet" href="http://localhost/design/css/recipesDetails.css" />
		<link rel="stylesheet" type="text/css" href="http://www.tgcost.com/skin/default/css/font-awesome.min.css" />
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
		<div class="main">
			<div class="main_nav">
				<div class="main_sec">
					<ul>
						<li>
							<a href="">首页</a>
						</li>
						<li>&gt;</li>
						<li>
							<a href="">健康饮食知识</a>
						</li>
						<li>&gt;</li>
						<li>
							<a href="">${entity.type }</a>
						</li>
					</ul>
				</div>
				<div class="knowledge_base_info">
					<h2 class="title"><a>${entity.name }</a></h2>
					<div class="base_info">
						<a href=""><img src="http://localhost/design/img/myDesignLogo.jpg" /></a>
						<div class="record_and_user_info">
							<span>作者：<a href="">LM513135</a></span>
							<span style="color: #ADADAD;">2017-04-28 / ${entity.clickCount }人看过 </span>
						</div>
					</div>
				</div>
				<div class="knowledge_details">
					<div class="edit editwz edit_class_0 edit_class_18" data-plugin="keyword">
						<p>
							${entity.content }
						</p>
					</div>
				</div>
				<!--<div class="comment_container">
					<form>
						<div class="comment_area">
							
							
						</div>
					</form>
				</div>-->
			</div>
		</div>
		<script type="text/javascript" src="http://localhost/design/js/jquery-3.2.1.min.js" ></script>
		<script type="text/javascript" src="http://localhost/design/js/common.js" ></script>
	</body>

</html>