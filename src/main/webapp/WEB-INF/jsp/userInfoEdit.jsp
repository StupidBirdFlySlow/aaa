<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="http://localhost/design/css/layui.css" />
	</head>

	<body>
		<div class="layui-body" style="width: 400px;position: absolute;left: 0px;">
			<form class="layui-form" style="margin-top: 20px;" id="editUserInfoForm">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-inline">
							<input type="text" name="username" value="${editUser.username }" lay-verify="required|phone" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">密码</label>
						<div class="layui-input-inline">
							<input type="password"" name="pwd" value="${editUser.pwd }" lay-verify="required|phone" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">邮箱</label>
						<div class="layui-input-inline">
							<input type="email" name="email" value="${editUser.email }" lay-verify="email" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">电话</label>
						<div class="layui-input-inline">
							<input type="tel" name="phone" value="${editUser.phone }" lay-verify="required|phone" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">城市</label>
						<div class="layui-input-inline">
							<input type="text" name="city" value="${editUser.city }" lay-verify="required|phone" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">省份</label>
						<div class="layui-input-inline">
							<input type="text" name="province" value="${editUser.province }" lay-verify="required|phone" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<input type="hidden" value="${editUser.id }" id="editUserId"/>
				<c:if test="${action=='edit'}">
					<div class="layui-input-block">
						<input type="button" id="editUserInfo" class="layui-btn" value="立即提交">
					</div>
				</c:if>
				</div>
			</form>
		</div>
	</body>
	<script type="text/javascript" src="http://localhost/design/js/jquery-3.2.1.min.js" ></script>
	<script src="http://localhost/design/js/layui.js"></script>
	<script type="text/javascript">
		layui.use('layer', function(){
		  var layer = layui.layer;
		  
		});
		$("#editUserInfo").click(function(){
			$.ajax({
				type:'post',
				url:'http://localhost/design/server/edit.json?userId='+$("#editUserId").val(),
				data:$("#editUserInfoForm").serialize(),
				success:function(result){
					layer.msg(result.item);
				}
			})
		})
	</script>

</html>