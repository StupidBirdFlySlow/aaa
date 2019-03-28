<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" href="http://localhost/design/css/login.css" />
		<link rel="stylesheet" href="http://localhost/design/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="http://www.tgcost.com/skin/default/css/font-awesome.min.css" />
		<link rel="stylesheet" href="http://localhost/design/css/common.css" />
		<title>Login Page</title>
	</head>

	<body>
		<div class="grid">
			<div class="header">
				<a href="#"><img  class="logo" src="http://localhost/design/img/myDesignLogo.jpg"/></a>
			</div>
			<div class="main">
				<img src="http://localhost/design/img/logo.png" class="content_left" />
				<div class="content_right">
					<div class="login_box">
						<div class="msg_div" id="msg" style="display:none">
							<p class="msg_info"><i class="fa fa-warning"></i>&nbsp;&nbsp;<sapn id="info">账号或密码错误，请重新输入<span></span></p>
						</div>
						<div class="box_header">
							<span id="ptLogin" class="login_way selected" >普通方式登录</span>
							<sapn id="yzmLogin" class="login_way yzm">手机验证码登录&nbsp;<i class="fa fa-mobile-phone size"></i></span>
						</div>
						<div class="box_content">
							<!-- 手机号码登陆 -->
							<form method="post" id="phoneLoginForm" action="phone_login.html"  style="display:none">
								<div class="input_div" id="phone">
									<i class="fa fa-user-o ico_logo"></i>
									<input type="text" name="phone" placeholder="手机号" class="input_item" />
								</div>
								<div class="input_div" id="dtm">
									<i class="fa fa-lock ico_logo"></i>
									<input type="text" name="dtm" placeholder="动态码" class="input_item" />
									<a href="" class="getDtm">获取动态码</a>
								</div>
								<div class="input_div">
									<input type="checkbox" checked="checked"/>
									<label class="msg_label">记住密码</label>
									<a href="#" class="msg_label fpwd">忘记密码</a>
								</div>
								<div class="input_div">
									<input type="submit" class="loginBtn" value="登&nbsp;&nbsp;录"/>
								</div>
								<div class="input_div">
									<label class="msg_label">还没有账号？</label><a href="#" style="font-size: 0.8em;position: relative;top: -0.12em;">免费注册</a>
								</div>
								<div class="input_div">
									<a href="#" class="third_login"><i class="fa fa-qq"></i>QQ登录</a>
									<a href="#" class="third_login"><i class="fa fa-wechat"></i>微信登录</a>
								</div>
							</form>
							<!-- 帐号密码登陆 -->
							<input type="text" hidden="hidden" id="code" value="${code}" name="code"/>
							<form method="post" id="pwdLoginForm" action="">
								<div id="account" class="input_div"">
									<i class="fa fa-user-o ico_logo"></i>
									<input type="text" id="username" name="username" placeholder="手机号/用户名/邮箱" class="input_item" />
								</div>
								<div class="input_div">
									<i class="fa fa-lock ico_logo"></i>
									<input type="password" id="pwd" name="pwd" placeholder="密码" class="input_item" />
								</div>
								<div class="input_div">
									<input type="text" id="yzm" name="yzm" placeholder="验证码"  class="input_yzm"/>
									<img src="http://localhost/design/img/yzm.png" id="yzmImg" class="yzm_img" />
									<a href="javascript:;" id="changeYzm" class="msg_label">看不清楚，换一张</a>
								</div>
								<div class="input_div">
									<input type="checkbox" checked="checked"/>
									<label class="msg_label">记住密码</label>
									<a href="#" class="msg_label fpwd">忘记密码</a>
								</div>
								<div class="input_div">
									<input id="ptLoginBtn" type="button" class="loginBtn" value="登&nbsp;&nbsp;录"/>
								</div>
								<div class="input_div">
									<label class="msg_label">还没有账号？</label><a href="#" style="font-size: 0.8em;position: relative;top: -0.12em;">免费注册</a>
								</div>
								<div class="input_div">
									<a href="#" class="third_login"><i class="fa fa-qq"></i>QQ登录</a>
									<a href="#" class="third_login"><i class="fa fa-wechat"></i>微信登录</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="footer">
				<span class="foot_content">Hunan Agriculture University   @Author leim  Directed by ZhangYinQiong</span>
			</div>
		</div>
		<script type="text/javascript" src="http://localhost/design/js/jquery-3.2.1.min.js" ></script>
		<script>
			$(function(){
				$("#ptLogin").click(function(){
					$("#ptLogin").addClass("selected");
					$("#yzmLogin").removeClass("selected");
					$("#phoneLoginForm").hide();
					$("#pwdLoginForm").show();
					$("#msg").hide();
				});
				$("#yzmLogin").click(function(){
					$("#yzmLogin").addClass("selected");
					$("#ptLogin").removeClass("selected");
					$("#phoneLoginForm").show();
					$("#pwdLoginForm").hide();
					$("#msg").hide();
				});
				
				$("#ptLoginBtn").click(function(){
					if(checkUsername()){
						if(checkPwd()){
							if(checkYzm()){
								//$("#pwdLoginForm").submit();
								$.ajax({
									type:'post',
									url:'http://localhost/design/pwd_login.json',
									data:$("#pwdLoginForm").serialize(),
									success:function(result){
										if(result.item==true){
											$("#msg").hide();
											window.location.href="http://localhost/design/home.html";//跳转到首页
										}else{
											$("#info").html(result.item);
											$("#msg").show();
										}
									}
								});
							}
						}
					}
				});
				
				$("#changeYzm").click(function(){
					$.ajax({
						url:'http://localhost/design/changeYzm.json',
						type:'post',
						success:function(result){
							$("#yzmImg").attr("src","http://localhost/design/img/yzm.png?flag="+Math.random());
							var code=result.item;
							$("#code").val(code);
						}
					})
				})
			})
			function checkUsername(){
				var username=$("#username").val();
				if(username!=null&&username!=""){
					$("#msg").hide();
					return true;
				}else{
					$("#info").html("用户名不能为空！");
					$("#msg").show();
					return false;
				}
			}
			function checkPwd(){
				var pwd=$("input[type=password]").val();
				if(pwd!=null&&pwd!=""){
					$("#msg").hide();
					return true;
				}else{
					$("#info").html("密码不能为空！");
					$("#msg").show();
					return false;
				}
			}
			function checkYzm(){
				var yzm=$("#yzm").val();
				var code=$("#code").val();
				if(yzm!=null&&yzm!=""){
					if(yzm.toLowerCase()==code.toLowerCase()){
						$("#msg").hide();
						console.log("yzm="+yzm+"   code="+code);
						return true;
					}else{
						$("#info").html("验证码不正确！");
						$("#msg").show();
						return false;
					}
				}else{
					$("#info").html("验证码不能为空！");
					$("#msg").show();
					return false;
				}
			}
		</script>
	</body>
</html>