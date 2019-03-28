<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/common.css">
		<link rel="stylesheet" href="css/home.css" />
		<link rel="stylesheet" type="text/css" href="http://www.tgcost.com/skin/default/css/font-awesome.min.css" />
		<title>首页</title>
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
								<a href="http://localhost/design/server/index.html"><span><i class="fa fa-server"></i> 服务中心</span></a>
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
				<li class="current">
					<a href="http://localhost/design/home.html" class="nav_link"><strong>首页</strong></a>
				</li>
				<li>
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

		<!--	正式内容		-->
		<!--今日推荐-->
		<div class="main_content">
			<h2 class="daily_commnad_title">每日推荐</h2>
			<div class="daily_meal_command">
				<ul class="daily_meal_command_nav" id="daily_recommand_nav">
					<li class="on">
						<a href="javascript:;" id="zhaocantj">早餐推荐</a>
					</li>
					<li>
						<a href="javascript:;" id="zhongcantj">中餐推荐</a>
					</li>
					<li>
						<a href="javascript:;" id="xiawuchatj">下午茶推荐</a>
					</li>
					<li>
						<a href="javascript:;" id="wancantj">晚餐推荐</a>
					</li>
					<li>
						<a href="javascript:;" id="yexiaotj">夜宵推荐</a>
					</li>
				</ul>
				<div class="daily_recommand">
					<div class="recommand_container" style="position: relative;left: 0px;" id="daily_recommand">
						<!--早餐-->
						<div class="daily_command_top">
						<c:forEach items="${zaocan }" var="item">
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
						<!-- zhongcan -->
						<div class="daily_command_top">
						<c:forEach items="${zhongcan }" var="item">
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
						<!--下午茶-->
						<div class="daily_command_top">
						<c:forEach items="${xiawucha }" var="item">
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
						<!--wancan-->
						<div class="daily_command_top">
						<c:forEach items="${wancan }" var="item">
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
						<!-- yexiao -->
						<div class="daily_command_top">
						<c:forEach items="${xiaoye }" var="item">
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
					</div>
				</div>
			</div>

			<!--最新最热菜谱-->
			<h2 class="daily_commnad_title">最新最热菜谱</h2>
			<span class="reces_recommand_nav more_tip">
				<a href="javascript:;" id="recipes_recommands_0" class="on_play" onmouseover="handPlay(0)">最新</a>
				<span> | 最热:</span>
				<a href="javascript:;" id="recipes_recommands_1" onmouseover="handPlay(1)">本周</a>&nbsp;&nbsp;
				<a href="javascript:;" id="recipes_recommands_2" onmouseover="handPlay(2)">本月</a>
			</span>
			<div class="daily_recommand">
				<div class="recommand_container" style="position: relative;left: 0px;" id="recommand_recipes">
					<!--最新-->
					<div class="daily_command_top">
						<c:forEach items="${mostNewRecipes }" var="item">
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
					<!--最热：本周-->
					<div class="daily_command_top">
						<c:forEach items="${thisWeek }" var="item">
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
					<!--最热：本月-->
					<div class="daily_command_top">
						<c:forEach items="${thisMonth }" var="item">
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
				</div>
			</div>
			<!--轮播标志-->
			<div class="recipes_recommand_auto_play">
				<i class="recipes_play_logo playing" id="recipes_play_0" onmouseover="handPlay(0)"></i>
				<i class="recipes_play_logo" id="recipes_play_1" onmouseover="handPlay(1)"></i>
				<i class="recipes_play_logo" id="recipes_play_2" onmouseover="handPlay(2)"></i>
			</div>

			<!--食材百科-->
			<h2 class="daily_commnad_title">食材百科</h2>
			<span class="more_tip"><a href="http://localhost/design/shicaibaike/index.html">更多食材&nbsp;&gt;</a></span>
			<div class="home_scbk">
				<ul class="scbk_nav">
					<li class="on">
						<a href="javascript:;">蔬菜</a>
					</li>
					<li>
						<a href="javascript:;">水果</a>
					</li>
					<li>
						<a href="javascript:;">五谷</a>
					</li>
					<li>
						<a href="javascript:;">生鲜</a>
					</li>
				</ul>
				<div class="scbk_show">
					<div class="scbk_show_container">
						<div class="scbk_show_frame">
						<c:forEach items="${shucai }" var="item">
							<div class="scbk_show_item">
								<a href="http://localhost/design/shicaibaike/details/${item.id }.html">
									<img src="${item.img_url }${item.img_name}" class="scbk_img" />
									<div class="scbk_desc">
										<a href="" class="sc_name"><i class="fa fa-stop"></i>&nbsp;${item.name }</a>
									</div>
								</a>
							</div>
						</c:forEach>
						</div>
						<!---->
						<div class="scbk_show_frame">
						<c:forEach items="${shuiguo }" var="item">
							<div class="scbk_show_item">
								<a href="http://localhost/design/shicaibaike/details/${item.id }.html">
									<img src="${item.img_url }${item.img_name}" class="scbk_img" />
									<div class="scbk_desc">
										<a href="" class="sc_name"><i class="fa fa-stop"></i>&nbsp;${item.name }</a>
									</div>
								</a>
							</div>
						</c:forEach>
						</div>
						<!---->
						<div class="scbk_show_frame">
						<c:forEach items="${gulei}" var="item">
							<div class="scbk_show_item">
								<a href="http://localhost/design/shicaibaike/details/${item.id }.html">
									<img src="${item.img_url }${item.img_name}" class="scbk_img" />
									<div class="scbk_desc">
										<a href="" class="sc_name"><i class="fa fa-stop"></i>&nbsp;${item.name }</a>
									</div>
								</a>
							</div>
						</c:forEach>
						</div>
						<!---->
						<div class="scbk_show_frame">
						<c:forEach items="${yxbx }" var="item">
							<div class="scbk_show_item">
								<a href="http://localhost/design/shicaibaike/details/${item.id }.html">
									<img src="${item.img_url }${item.img_name}" class="scbk_img" />
									<div class="scbk_desc">
										<a href="" class="sc_name"><i class="fa fa-stop"></i>&nbsp;${item.name }</a>
									</div>
								</a>
							</div>
						</c:forEach>
							<!--<div class="scbk_show_item">
								<a href="">
									<img src="img/1caad250e69a65e95f619e07eb55da46_150x150.jpg" class="scbk_img" />
									<div class="scbk_desc">
										<a href="" class="sc_name"><i class="fa fa-stop"></i>&nbsp;大闸蟹</a>
									</div>
								</a>
							</div> -->
						</div>
					</div>
				</div>
			</div>
			<!--饮食知识-->
			<h2 class="daily_commnad_title">饮食知识</h2>
			<span class="more_tip"><a href="http://localhost/design/knowledge/index.html">更多饮食知识&nbsp;&gt;</a></span>
			<div class="daily_command_top yszs_style">
				<c:forEach items="${knowledge }" var="item">
					<div class="daily_command_item">
						<a href="http://localhost/design/knowledge/details/${item.id }.html" target="_blank" class="command_recipes">
							<img src="${item.img_url }${item.img_name}" class="daily_command_item_img" />
							<div class="recipes_info">
								<strong>${item.name }</strong>
								<span>0 评论  ${item.clickCount } 人气</span>
								<em>近乡情怯</em>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="footer">
			<span class="foot_content">Hunan Agriculture University   @Author leim  Directed by ZhangYinQiong</span>
		</div>
		<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script>
			$(function() {
				$("#daily_recommand_nav").find('li').each(function() {
					$(this).click(function() {
						$(this).addClass("on");
						$(this).siblings().removeClass("on");
						var index = $(this).index();
						//alert(index);
						var left = index * 995 * -1;
						//alert(left);
						$("#daily_recommand").animate({
							left: left + 'px'
						}, 500);
					});
				});
				$(".scbk_nav").find('li').each(function() {
					$(this).click(function() {
						$(this).addClass("on");
						$(this).siblings().removeClass("on");
						var index = $(this).index();
						var left = index * 1005 * -1;
						$(".scbk_show_container").animate({
							left: left + 'px'
						}, 500);
					})
				});
				
			})
		</script>
		<script>
			var time=0;
			var index=0;
			window.onload=autoPlay;
			function autoPlay(){
				time=setInterval(play,5000);
			}
			
			function play(){
				clearInterval(time);
				index=(index+1)%3;
				var b1=(index+1)%3;
				var b2=(index+2)%3;
				var left=index*995*-1;
				$("#recommand_recipes").animate({
					left:left+'px'
				},500);
				$("#recipes_recommands_"+index).addClass("on_play");
				$("#recipes_recommands_"+b1).removeClass("on_play");
				$("#recipes_recommands_"+b2).removeClass("on_play");
				
				$("#recipes_play_"+index).addClass("playing");
				$("#recipes_play_"+b1).removeClass("playing");
				$("#recipes_play_"+b2).removeClass("playing");
				time=setInterval(play,5000);
			}
			
			function handPlay(location){
				clearInterval(time);
				index=location-1;
				index=(index+1)%3;
				var b1=(index+1)%3;
				var b2=(index+2)%3;
				var left=index*995*-1;
				$("#recommand_recipes").animate({
					left:left+'px'
				},500);
				$("#recipes_recommands_"+index).addClass("on_play");
				$("#recipes_recommands_"+b1).removeClass("on_play");
				$("#recipes_recommands_"+b2).removeClass("on_play");
				
				$("#recipes_play_"+index).addClass("playing");
				$("#recipes_play_"+b1).removeClass("playing");
				$("#recipes_play_"+b2).removeClass("playing");
				time=setInterval(play,5000);
			}
		</script>
	</body>

</html>