<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>注册页面</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/register.css" />
		<link rel="stylesheet" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="http://www.tgcost.com/skin/default/css/font-awesome.min.css" />
	</head>

	<body>
		<div class="grid">
			<!--  注册页面 头部	-->
			<div class="header head_top">
				<div class="register_logo_div">
					<img src="img/myDesignLogo.jpg" class="register_logo" />
					<div class="head_top_right">
						<span>已有账号？</span>
						<button class="head_top_login">登&nbsp;录</button>
					</div>
				</div>
			</div>
			<!-- 注册页面主体  -->
			<div class="main">
				<div class="register_content">
					<div class="register_main">
						<div class="third_login">
							<h5 class="login_title">—————— 第三方登录  ——————</h5>
							<a href="#"><i class="fa fa-qq login_way">QQ登录</i></a>
							<a href="#"><i class="fa fa-wechat login_way">微信登录</i></a>
						</div>
						<div class="register_box">
							<span class="register_way sjzc_way" id="phone_register">手机注册</span> &nbsp;&nbsp;|&nbsp;&nbsp;
							<span href="#" class="register_way selected" id="mail_register">邮箱注册</span>
							<!-- 手机号码注册 -->
							<form style="display: none;" id="phone_register_form" method="post" action="" onkeydown="if(event.keyCode==13){return false;}">
								<div class="register_item">
									<input type="text" name="phone" placeholder="请输入手机号码" class="input_item" id="phone"  onblur="checkPhone()"/>
									<!--style="display: none;"-->
									<i class="fa fa-times-circle input_msg check_error" id="phoneMsgError" style="display: none"></i>
									<i class="fa fa-check-circle input_msg check_right" id="phoneMsgTrue" style="display: none"></i>
								</div>
								<div class="register_item">
									<input type="password" name="pwd" placeholder="请输入密码" class="input_item" id="pwdphone"/>
									<i class="fa fa-times-circle input_msg check_error" id="phonePwdMsg" style="display: none"></i>
								</div>
								<!-- <div class="register_item">
									<input type="text" name="yzm" placeholder="请输入验证码" class="input_item_yzm" />
									<input type="button" value="获取验证码" class="button_yzm" />
									<i class="fa fa-times-circle input_msg check_error">验证码错误，请重新输入</i>
									<br>
									<i class="fa fa-check-circle input_msg check_right">验证码正确</i>
								</div> -->
								<div class="register_item">
									<input type="button" value="注册" class="register_btn"  id="phone_registerBtn"/>
								</div>
							</form>
							<!-- 邮箱注册 -->
							<form id="mail_register_form" method="post" action="" onkeydown="if(event.keyCode==13){return false;}">
								<div class="register_item">
									<input type="text" name="email" placeholder="请输入邮箱" class="input_item" id="email" onblur="checkEmail()"/>
									<!--style="display: none;"-->
									<i class="fa fa-times-circle input_msg check_error" id="emailMsgError" style="display: none"></i>
									<i class="fa fa-check-circle input_msg check_right" id="emailMsgTrue" style="display: none"></i>
								</div>
								<div class="register_item">
									<input type="password" name="pwd" placeholder="请输入密码" class="input_item" id="pwdemail"/>
									<!--style="display: none;"-->
									<i class="fa fa-times-circle input_msg check_error" id="emailPwdMsg" style="display: none"></i>
								</div>
								<div class="register_item">
									<input type="button" value="注册" class="register_btn" id="mail_registerBtn"/>
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
		<script type="text/javascript" src="js/jquery-3.2.1.min.js" ></script>
		<script>
			$(function(){
				$("#phone_register").click(function(){
					$("#phone_register_form").show()
					$("#mail_register_form").hide();
					$("#mail_register").removeClass("selected");
					$("#phone_register").addClass("selected");
				});
				$("#mail_register").click(function(){
					$("#phone_register_form").hide()
					$("#mail_register_form").show();
					$("#phone_register").removeClass("selected");
					$("#mail_register").addClass("selected");
				});
				
				$("#mail_registerBtn").click(function(){
					if(checkMailForm()){
						$.ajax({
							type:'post',
							dataType:'json',
							url:'mail_register.json',
							data:$("#mail_register_form").serialize(),
							success:function(result){
								window.location.href="login.html";//跳转到登录页面
							},
							error:function(result){
								console.log("注册失败");
							}
						}) 
					}
				});
				
				$("#phone_registerBtn").click(function(){
					if(checkPhoneForm()){
						$.ajax({
							type:'post',
							dataType:'json',
							url:'phone_register.json',
							data:$("#phone_register_form").serialize(),
							success:function(result){
								window.location.href="login.html";//跳转到登录页面
							},
							error:function(result){
								console.log("注册失败");
							}
						}) 
					}
				})
				
			});
			
			function checkMailForm(){
				if(checkPwd("email")&&checkIsNull("email")){
					return true;
				}else{
					return false;
				}
			}
			function checkPhoneForm(){
				if(checkPwd("phone")&&checkIsNull("phone")){
					return true;
				}else{
					return false;
				}
			}
			function checkIsNull(id){
				var val=$("#"+id).val();
				if(val!=null&&val!=""){
					$("#"+id+"MsgError").hide();
					return true;
				}else{
					$("#"+id+"MsgError").html("内容不能为空");
					$("#"+id+"MsgError").show();
				}
				
			}
			/*检查邮箱格式*/
			function checkEmail(){
				var email=$("#email").val();
				var index=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
				if(index.test(email)){
					$.ajax({
						type:'post',
						dataType:'json',
						url:'checkEmail.json',
						data:{email:email},
						success:function(result){
							if(result==false){
								$("#emailMsgError").hide();
								$("#emailMsgTrue").html("该邮箱可用");
								$("#emailMsgTrue").show();
								return true;
							}else{
								$("#emailMsgTrue").hide();
								$("#emailMsgError").html("该邮箱地址已被占用");
								$("#emailMsgError").show();
								return false;
							}
						}
					})
				}else{
					$("#emailMsgError").html("请输入正确的邮箱地址");
					$("#emailMsgTrue").hide();
					$("#emailMsgError").show();
					return false;
				}
			}
			
			/*检查密码格式*/
			function checkPwd(item){
				var pwd=$("#pwd"+item).val();
				var index=/^\w{6,16}$/;
				if(index.test(pwd)){
					$("#"+item+"PwdMsg").hide();
					return true;
				}else{
					$("#"+item+"PwdMsg").html("密码的长度应为6-16位");
					$("#"+item+"PwdMsg").show();
					return false;
				}
			}
			
			/*检查电话号码*/
			function checkPhone(){
				var phone=$("#phone").val();
				var index=/^[0-9]{11}$/;
				if(index.test(phone)){
					$.ajax({
						type:'post',
						url:'checkPhone.json',
						data:{phone:phone},
						success:function(result){
							if(result=="false"){
								$("#phoneMsgError").hide();
								$("#phoneMsgTrue").html("该手机号码可用");
								$("#phoneMsgTrue").show();
								return true;
							}else{
								$("#phoneMsgTrue").hide();
								$("#phoneMsgError").html("该手机号码已被占用");
								$("#phoneMsgError").show();
								return false;
							}
						}
					})
				}else{
					$("#phoneMsgError").html("请输入正确的手机号码");
					$("#phoneMsgTrue").hide();
					$("#phoneMsgError").show();
					return false;
				}
			}
		</script>
	</body>

</html>