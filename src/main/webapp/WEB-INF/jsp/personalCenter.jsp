<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="http://localhost/design/css/personalCenter.css" />
		<link rel="stylesheet" href="http://localhost/design/css/common.css" />
		<link rel="stylesheet" href="http://localhost/design/css/home.css" />
		<link rel="stylesheet" type="text/css" href="http://www.tgcost.com/skin/default/css/font-awesome.min.css" />
		<link rel="stylesheet" type="text/css" href="http://res.tgcost.com:18080/widget/layer/skin/layer.css" />
		<link rel="stylesheet" href="http://localhost/design/css/page.css" />
		<title>个人中心</title>
		<style type="text/css">
			.settings{
				width:79px;
				position: absolute;
				top:0px;
				left:930px;
				top:50px;
				display: none;
				border:solid 1px #00BFFF;
			}
			.settings_button{
				width:78px;
				height:29px;
				border:solid 1px white;
				background-color: #00BFFF;
				color:white;
			}
			.saveHeadImg{
				position:relative;
				left:20px;
			}
		</style>
	</head>
	<body>
		<div class="head_top">
			<div class="head">
				<div class="gr_info">
					<h4 class="logo_head"><a href="http://localhost/design/home.html">首页</a></h4>
					<h4 class="logo_head"><a href="http://localhost/design/healthyRecipes/index.html">健康食谱</a></h4>
					<h4 class="logo_head"><a href="http://localhost/design/knowledge/index.html">健康饮食知识</a></h4>
					<h4 class="logo_head"><a href="http://localhost/design/shicaibaike/index.html">食材百科</a></h4>
				</div>
				<div class="gr_info2">
					<a href="http://localhost/design/personalCenter/${user.id }.html" id="user_info">
						<img src="${user.head_img }${user.head_img_name}" class="gr_head_img" />
						<span>${user.username }</span>
						<input type="hidden" id="userId" value="${user.id }"/>
					</a>
				</div>
			</div>
		</div>
		<!--正式内容-->
		<div class="gr_center">
			<div class="content_left">
				<!--	导航栏		-->
				<div class="person_operator_nav">
					<ul>
						<li>
							<a href="javascript:;" id="personInfo">个人信息<span>&gt;</span></a>
						</li>
						<li>
							<a href="javascript:;" id="changePwd">修改密码<span>&gt;</span></a>
						</li>
						<li>
							<a href="javascript:;" id="recipesCollection">菜谱收藏<span>&gt;</span></a>
						</li>
						<li>
							<a href="javascript:;" id="recipesShare">菜谱分享<span>&gt;</span></a>
						</li>
						<li>
							<a href="javascript:;" id="changeHeadImg">头像修改<span>&gt;</span></a>
						</li>
					</ul>
				</div>
			</div>
			<div class="content_right">
				<div class="settings">
					<input type="button" id="tuichu" value="退出登录" class="settings_button"/>
				</div>
				<div class="person_info_details">
					<div class="titles_div">
						<span class="person_details_title"><strong id="panelTitle">菜谱收藏</strong></span>
					</div>
					<div class="person_info_details_show">
						<!--个人信息-->
						<div class="details_container" id="person_info" style="display: none;">
							<form method="post" action="#" id="userInfoForm">
								<div class="details_item">
									<span class="">昵称：</span>
									<input type="text" name="username" id="username" class="readOnly" value="${user.username }" readonly="readonly" disabled="disabled" />
								</div>
								<div class="details_item">
									<span class="">邮箱:</span>
									<input type="text" name="email" id="email" class="readOnly" value="${user.email }" readonly="readonly" disabled="disabled" />
								</div>
								<div class="details_item">
									<span class="">手机号码:</span>
									<input type="text" name="phone" id="phone" class="readOnly" value="${user.phone }" readonly="readonly" disabled="disabled" />
								</div>
								<div class="details_item">
									<span class="">性别：</span>
									<input name="gender" value="${user.gender }" id="gender" hidden="hidden"/>
									<input  class="gender" id="gender_nan" type="radio" name="sex1" <c:if test="${user.gender== '男'}">checked</c:if> /><span class="gender_nan">男</span>
									<input  class="gender" id="gender_nv" type="radio" name="sex1"  <c:if test="${user.gender== '女'}">checked</c:if> /><span class="gender_nan">女</span>
								</div>
								<div class="details_item">
									<span class="">所在地：</span>
									<div class="year_month_day">
										<select name="province" id="provinceList">
											<c:forEach items='${provinceList }' var='item'>
												<option <c:if test='${item==user.province }'>selected</c:if>>${item}</option>
											</c:forEach>
										</select>
										<select name="city" id="cityList">
											<c:forEach items="${countryList}" var="item">
												<option <c:if test="${item==user.city }">selected</c:if>>${item}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="details_item">
									<span></span>
									<input class="btn_change" id="modifyUserInfo" value="修改" type="button" />
									<input class="btn_change readOnly" id="saveChanges" value="保存" type="button" disabled="disabled" />
								</div>	
							</form>
						</div>
						<!--修改密码-->
						<div class="details_container" id="change_pwd" style="display: none;">
							<form method="post" id="changePwdForm">
								<div class="change_pwd_item">
									<span>原密码：</span>
									<input type="password" name="oldpwd" placeholder="请输入原密码" />
								</div>
								<div class="change_pwd_item">
									<span>新密码：</span>
									<input type="password" name="newpwd" placeholder="请输入新密码" />
								</div>
								<div class="change_pwd_item">
									<span>确认密码：</span>
									<input type="password" name="snewpwd" placeholder="请输入确认密码" />
								</div>
								<div class="change_pwd_item">
									<input id="changePwdBtn" type="button" value="确&nbsp;&nbsp;&nbsp;&nbsp;定" style="text-align: center;" class="change_pwd_submit_btn" />
								</div>
							</form>
						</div>
						<!--菜谱收藏-->
						<div class="details_container" id="recipes_collection" style="display: block;">
							<div class="my_recipes_collection_show" style="position: relative;top: 50px;left:-8px;">
								<c:forEach items="${collectionList }" var="item">
								<div class="daily_command_item" style="margin-left:20px;">
									<a href="http://localhost/design/healthyRecipes/details/${item.cpId }.html" target="_blank" class="command_recipes">
										<img src="${item.cpImg_url }${item.cpImg_name}" class="daily_command_item_img" />
										<div class="recipes_info">
											<strong>${item.cpname }</strong>
											<span>0 评论   ${item.clickCount }人气</span>
											<em>近乡情怯</em>
										</div>
									</a>
									<strong style="position: relative;left:150px;top:-30px;font-size:14px;cursor: pointer;"><a  href="http://localhost/design/user/cancelCollection.html?id=${item.id }&userId=${item.userId}">取消收藏 </strong>
								</div>
								</c:forEach>
							</div>
							<!--分页-->
							<div class="listtyle1_page">
								<div class="listtyle1_page_w">
									<c:forEach  varStatus="s" begin="1" end="6">
										<a href="javascript:;" onclick="getPage(${s.count})" class="" id="page${s.count }">${s.count}</a>
									</c:forEach>
								</div>
							</div>
						</div>
						<!--菜谱分享-->
						
						<!-- 修改头像 -->
						<div class="details_container" id="change_head_img" style="display: block;">
							<div class="currentImg">
								<span>当前的头像为：</span>
								<img alt="" src="${user.head_img }${user.head_img_name}">
							</div>
							<form enctype="multipart/form-data" method="post" id="changeHeadImgForm">
							<div class="currentImg">
								<span>选择新头像：</span>
								<img alt="" src="http://localhost/design/img/myDesignLogo.jpg" id="choice_head_img_img">
								<input type="file" hidden id="choice_head_img_file" name="file" />
							</div>
							<div class="currentImg">
								<input type="button" id="change_img_submit_btn" value="保存" class="settings_button saveHeadImg">
							</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<span class="foot_content">Hunan Agriculture University   @Author leim  Directed by ZhangYinQiong</span>
		</div>
		<script type="text/javascript" src="http://localhost/design/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="http://res.tgcost.com:18080/widget/layer/layer.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#personInfo").click(function(){
					$(".details_container").hide();
					$("#panelTitle").html("个人信息");
					$("#person_info").show();
				});
				$("#changePwd").click(function(){
					$(".details_container").hide();
					$("#panelTitle").html("修改密码");
					$("#change_pwd").show();
				});
				$("#recipesCollection").click(function(){
					$(".details_container").hide();
					$("#panelTitle").html("菜谱收藏");
					$("#recipes_collection").show();
				});
				$("#recipesShare").click(function(){
					$(".details_container").hide();
					$("#panelTitle").html("菜谱分享");
					$("#recipes_share").show();
				});
				
				$("#changeHeadImg").click(function(){
					$(".details_container").hide();
					$("#panelTitle").html("修改头像");
					$("#change_head_img").show();
				})
				
				$("#modifyUserInfo").click(function(){
					$(".readOnly").removeAttr("readOnly").removeAttr("disabled");
				});
				
				$("#gender_nan").click(function(){
					$("#gender").val("男");
				});
				$("#gender_nv").click(function(){
					$("#gender").val("女");
				})
				
				$("#saveChanges").click(function(){
					$.ajax({
						url:'http://localhost/design/user/update.json',
						data:$("#userInfoForm").serialize(),
						type:'post',
						success:function(result){
							console.log(result.item);
							layer.msg(result.item);
						},
						error:function(result){
							console.log("update fail.....");
						}
					})
				})
				$("#provinceList").change(function(){
					$.ajax({
						url:'http://localhost/design/user/loadCity.json',
						data:{province:$("#provinceList option:selected").val()},
						type:'post',
						success:function(result){
							console.log(result);
							var cityList=result.item;
							$("#cityList").empty();
							for(var i=0;i<cityList.length;i++){
								$("#cityList").append("<option>"+cityList[i]+"</option>");
							}
						},
						error:function(result){
							console.log("load cityList fail.....");
						}
					})
				});
				
				$("#changePwdBtn").click(function (){
					$.ajax({
						url:"http://localhost/design/user/changePwd.json",
						data:$("#changePwdForm").serialize(),
						type:'post',
						success:function(result){
							layer.msg(result.item);
							$(":input","#changePwdForm")
							.not(":button",":reset","hidden","submit")
							.val("")
							.removeAttr("checked")
							.removeAttr("selected");
						},
						error:function(result){
							console.log("changePwd fail.....");
						}
					})
				});
				
				$("#choice_head_img_img").click(function(){
					$("#choice_head_img_file").click();
				})
				
				$("#change_img_submit_btn").click(function(){
					var formdata=new FormData($("#changeHeadImgForm")[0]);
					$.ajax({
						type:"post",
						url:"http://localhost/design/changeHeadImg.json",
						data:formdata,
						cache: false,  
		                processData: false,  
		                contentType: false, 
						success:function(result){
							window.location.href="http://localhost/design/personalCenter/${user.id}.html";//跳转到首页
						}
					})
				});
				/* 
				$("#user_info").mouseover(function(){
					$(".settings").show();
				}) */
				
				/* $("#tuichu").click(function(){
					$.ajax({
						url:"http://localhost/design/tuichu.html",
						type:'post',
						success:function(result){
							window.location.href="http://localhost/design/login.html";
						}
					})
				}); */
			})
			
			
			function getPage(index){
				$("#page"+index).addClass("current");
				$("#page"+index).siblings().removeClass("current");
				var left=(index-1)*760*-1-8-(index-1)*15;
				$(".my_recipes_collection_show").animate({
					left:left+'px'
				},500);
			}
			
			function cancelCollection(id){
				$.ajax({
					url:"http://localhost/design/user/cancelCollection.json",
					data:{id:id,userId:$("#userId").val()},
					type:'post',
					success:function(result){
						console.log(result.items[0]);
					}
				})
			}
			
		</script>
	</body>

</html>