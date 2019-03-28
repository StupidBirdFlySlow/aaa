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
		<div class="layui-body" style="width: 700px;position: absolute;left: 0px;">
			<form class="layui-form" style="margin-top: 20px;" id="recipesEditForm">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">菜谱名称</label>
						<div class="layui-input-inline">
							<input type="text" name="cpname" value="${menuDetails.cpname }" lay-verify="cpname" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">工艺</label>
						<div class="layui-input-inline">
							<input type="text" name="gy" value="${menuDetails.gy }" lay-verify=gy autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">难度</label>
						<div class="layui-input-inline">
							<input type="text" name="nd" value="${menuDetails.nd }" lay-verify="nd" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">用餐人数</label>
						<div class="layui-input-inline">
							<input type="text" name="ycrs" value="${menuDetails.ycrs }"  lay-verify="ycrs" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">烹饪时间</label>
						<div class="layui-input-inline">
							<input type="text" name="prsj" value="${menuDetails.prsj }" lay-verify="prsj" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">准备时间</label>
						<div class="layui-input-inline">
							<input type="text" name="zbsj" value="${menuDetails.zbsj }" lay-verify="zbsj" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">口味</label>
						<div class="layui-input-inline">
							<input type="text" name="kw"  value="${menuDetails.kw }" lay-verify="kw" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">菜谱描述</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入内容" name="descr" class="layui-textarea" style="width: 520px;">${menuDetails.descr }</textarea>
					</div>
				</div>
				<input type="hidden" value="${menuDetails.id }" id="recipesId"/>
				<c:if test="${action=='edit' }">
				<div class="layui-input-block">
					<input class="layui-btn" type="button" value="立即提交" id="recipesEditBtn">
				</div>
				</c:if>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="http://localhost/design/js/jquery-3.2.1.min.js" ></script>
		<script src="http://localhost/design/js/layui.js"></script>
		<script type="text/javascript">
		layui.use('layer', function(){
		  var layer = layui.layer;
		});
		$("#recipesEditBtn").click(function(){
			$.ajax({
				type:'post',
				url:'http://localhost/design/server/editRecipes.json?id='+$("#recipesId").val(),
				data:$("#recipesEditForm").serialize(),
				success:function(result){
					layer.msg(result.item);
				}
			})
		})
	</script>
		
	</body>

</html>