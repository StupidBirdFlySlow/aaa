<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c"%>	
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="http://localhost/design/css/common.css" />
		<link rel="stylesheet" href="http://localhost/design/css/recipesDetails.css" />
		<link rel="stylesheet" type="text/css" href="http://www.tgcost.com/skin/default/css/font-awesome.min.css" />
		<link rel="stylesheet" type="text/css" href="http://res.tgcost.com:18080/widget/layer/skin/layer.css" />
		<title>菜谱详情页面</title>

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
						<a href="" class="outer user_info_a"  style="display:inline-block; width:70px;left:20px;"><i class="fa fa-wechat"></i>QQ登录</a>
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
		<div class="main_first">
			<div class="main_sec">
				<ul>
					<li>
						<a href="">首页</a>
					</li>
					<li>&gt;</li>
					<li>
						<a href="">健康食谱</a>
					</li>
					<li>&gt;</li>
					<li>
						<a href="">${entity.cpname }</a>
					</li>
				</ul>
				<div class="cp_header">
					<div class="cp_header_img">
						<img alt="干锅土豆片" src="${entity.img_src }${entity.img_name}" />
					</div>
					<div class="cp_header_right">
						<div class="info1">
							<h1 style="margin-left: 20px; width:300px;height:42px;overflow: hidden;">${entity.cpname }</h1>
							<div class="share_and_collection">
								<span class="collectionBtn">
									<a href="javascript:;" id="recipesCollection">收藏<span> ( ${collectionCount } ) </span></a>
								</span>
							</div>
							<div class="func_list" >
								<c:forEach items="${entity.funcs}" var="item" begin="0" end="3">
									<div class="func_item">
										<a href="javascipt:;">${item.func }</a>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="info2">
							<ul>
								<li class="w104">
									<strong>工艺</strong>
									<a href="javasrcipt:;" class="big">${entity.gy }</a>
								</li>
								<li class="w104">
									<strong>难度</strong>
									<a href="javascript:;" class="big">${entity.nd }</a>
								</li>
								<li class="w104">
									<strong>人数</strong>
									<a href="javascript:;" class="big">${entity.ycrs }</a>
								</li>
								<li class="w104">
									<strong>口味</strong>
									<a href="javasrcipt:;" class="big">${entity.kw }</a>
								</li>
								<li class="w104">
									<strong>准备时间</strong>
									<a href="javasrcipt:;" class="big">${entity.zbsj }</a>
								</li>
								<li class="w104 br0">
									<strong>烹饪时间</strong>
									<a href="javasrcipt:;" class="big">${entity.prsj }</a>
								</li>
							</ul>
						</div>
						<div class="info3">
							<div class="cp_author">
								<a href="" target="_blank" class="author_img">
									<img src="https://s1.c.meishij.net/images/default/tx2_5.png">
								</a>
								<div class="author_info">
									<h4>
										<a href="" target="_blank">dihasdhjl</a>
									</h4>
									<span>菜谱：2 </span></br>
									<span>${entity.createTime } &nbsp;/&nbsp; ${entity.clickCount }人看过</span>
								</div>
							</div>
						</div>
						<div class="info4">
							<div class="cp_desc">
								<p><!-- <strong>“</strong>说点什么呢，两个字，好吃！<br>
									<br> 如果喜欢可以支持一下淘宝店铺，美的正品锅具优惠特卖！：https://myshow.ews.m.jaeapp.com/mshow/page?id=712544
									<strong>”</strong><br />
									<strong>“</strong>说点什么呢，两个字，好吃！<br>
									<br> 如果喜欢可以支持一下淘宝店铺，美的正品锅具优惠特卖！：https://myshow.ews.m.jaeapp.com/mshow/page?id=712544
									<strong>”</strong> -->
									${entity.descr }
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 菜谱用料 -->
			<div class="materials">
				<h3 class="cp_title">所需材料</h3>
				<div class="materials_content">
					<div class="zhuliao">
						<input type="button" value="材料" class="materials_btn main_btn" />
						<ul>
							<c:forEach items="${entity.materials }" var="item">
								<li>
									<a class="materials_img">
										<img src="${item.imgSrc}${item.imgName}">
									</a>
									<span class="materials_name">${item.clmc }</span>
									<span class="materials_quantity">${item.quantity }</span>
							</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<!--详细制作步骤-->
			<div class="cp_steps">
				<h3 class="cp_title">干锅土豆片的制作步骤</h3>
				<div class="steps_container">
				
					<c:forEach items="${entity.steps }" var="item">
						<div class="cp_step">
						<em class="step">${item.step_sort }</em>
						<div class="c">
							<p>${item.step_desc }</p>
							<p><img class="conimg" src="${item.step_img }${item.step_img_name}" alt=""></p>
						</div>
					</div>
					</c:forEach>
				
					<!-- <div class="cp_step">
						<em class="step">1.</em>
						<div class="c">
							<p>土豆削皮洗净</p>
						</div>
					</div>
					<div class="cp_step">
						<em class="step">2.</em>
						<div class="c">
							<p>切成均匀的片，不要太薄噢，锅里放水，水烧开以后把土豆片放进去烫一下，二十秒左右就可以了，烫久了做的时候容易烂掉，不烫也做不好</p>
							<p><img class="conimg" src="http://s1.st.meishij.net/rs/99/247/12686849/n12686849_152248777400501.jpg" alt=""></p>
						</div>
					</div>
					<div class="cp_step">
						<em class="step">3.</em>
						<div class="c">
							<p>锅里放油，把土豆片两面煎黄备用</p>
							<p><img class="conimg" src="http://s1.st.meishij.net/rs/99/247/12686849/n12686849_152248777542439.jpg" alt=""></p>
						</div>
					</div>
					<div class="cp_step">
						<em class="step">4.</em>
						<div class="c">
							<p>锅里加油烧热后，放蒜片，豆瓣酱适量，还可以放点花椒粒炒香</p>
							<p><img class="conimg" src="http://s1.st.meishij.net/rs/99/247/12686849/n12686849_152248777544344.jpg" alt=""></p>
						</div>
					</div>
					<div class="cp_step">
						<em class="step">5.</em>
						<div class="c">
							<p>把煎黄的土豆片倒进去翻均匀，撒上适量辣椒面翻匀，然后装干锅，撒葱花白芝麻，点上酒精炉即可</p>
							<p><img class="conimg" src="http://s1.st.meishij.net/rs/99/247/12686849/n12686849_152248777684687.jpg" alt=""></p>
						</div>
					</div> -->
				</div>
			</div>
			<div class="cp_comment">
				<h3 class="cp_title">干锅土豆片的评论</h3>
				<div class="">
					<form>
						<textarea placeholder="想说点什么...." class="comment_area"></textarea>
						<input type="submit" value="提交" class="comment_submit" />
					</form>
				</div>
				<div class="comment_list">
					<ul>
						<li>
							<p class="">
								<a href="" class="comment_user">username_1:</a>
								<span class="comment_content">healthy recipes comment content 很好</span>
							</p>
							<span class="comment_time">2018-04-23</span>
							<span class="comment_good"><i class="fa fa-thumbs-o-up"></i> 1</span>
						</li>
						<li>
							<p class="">
								<a href="" class="comment_user">username_1:</a>
								<span class="comment_content">healthy recipes comment content 很好</span>
							</p>
							<span class="comment_time">2018-04-23</span>
							<span class="comment_good"><i class="fa fa-thumbs-o-up"></i> 1</span>
						</li>
						<li>
							<p class="">
								<a href="" class="comment_user">username_1:</a>
								<span class="comment_content">healthy recipes comment content 很好</span>
							</p>
							<span class="comment_time">2018-04-23</span>
							<span class="comment_good"><i class="fa fa-thumbs-o-up"></i> 1</span>
						</li>
						<li>
							<p class="">
								<a href="" class="comment_user">username_1:</a>
								<span class="comment_content">healthy recipes comment content 很好</span>
							</p>
							<span class="comment_time">2018-04-23</span>
							<span class="comment_good"><i class="fa fa-thumbs-o-up"></i> 1</span>
						</li>

					</ul>
				</div>
			</div>
		</div>
		<div class="footer">
			<span class="foot_content">Hunan Agriculture University   @Author leim  Directed by ZhangYinQiong</span>
		</div>
		<input type="hidden" value="${entity.id }" id="recipeId"/>
		<script type="text/javascript" src="http://localhost/design/js/jquery-3.2.1.min.js" ></script>
		<script type="text/javascript" src="http://localhost/design/js/common.js" ></script>
		<script type="text/javascript" src="http://res.tgcost.com:18080/widget/layer/layer.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#recipesCollection").click(function (){
					$.ajax({
						url:"http://localhost/design/healthyRecipes/collection.json",
						data:{cpId:$("#recipeId").val()},
						type:"post",
						success:function(result){
							console.log(result);
							layer.msg(result.item);
						}
					})
				});
			});
		</script>
	</body>

</html>