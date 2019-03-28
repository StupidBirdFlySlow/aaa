<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>健康食谱后台服务</title>
  <link rel="stylesheet" href="http://localhost/design/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">健康食谱后台</div>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;"> <img src="${user.head_img }${user.head_img_name}" class="layui-nav-img">${user.username }</a>
        <dl class="layui-nav-child">
          <dd><a href="http://localhost/design/user/personalCenter/${user.id }.html">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="http://localhost/design/tuichu.html">退出</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">用户管理</a>
          <dl class="layui-nav-child">
            <dd><a href="http://localhost/design/server/userBaseInfo.html">用户基本信息</a></dd>
            <dd><a href="http://localhost/design/server/userInfoChange.html">用户信息修改</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">菜谱管理</a>
          <dl class="layui-nav-child">
            <dd><a href="http://localhost/design/server/recipesBaseInfo.html">菜谱基本信息</a></dd>
            <dd><a href="http://localhost/design/server/recipesInfoChange.html">菜谱信息修改</a></dd>
             <dd><a href="">菜谱下架</a></dd><!-- 修 改菜谱的状态 -->
          </dl>
        </li>
        <li class="layui-nav-item"><a href="">发布菜谱</a></li>
        <li class="layui-nav-item"><a href="">发布饮食知识</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
     <div class="layui-card-header">菜谱基本信息</div>
     <div class="layui-card-body">
        <table class="layui-hide" id="test"></table>
     </div>
    
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script src="http://localhost/design/js/layui.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
layui.use('table', function(){
  var table = layui.table;
  
  table.render({
    elem: '#test'
    ,url:'http://localhost/design/server/recipesInfo.json'
    ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
      layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
      //,curr: 5 //设定初始在第 5 页
      ,groups: 1 //只显示 1 个连续页码
      ,first: false //不显示首页
      ,last: false //不显示尾页
    }
    ,cols: [[
      {field:'cpname', width:200, title: '菜谱名'}
      ,{field:'gy', width:80, title: '工艺'}
      ,{field:'nd', width:120, title: '难度'}
      ,{field:'kw', title: '口味',width: 150 }
      ,{field:'prsj', width:120, title: '烹饪时间', sort: true}
      ,{field:'zbsj', width:120, title: '准备时间', sort: true}
      ,{field:'clickCount', width:120, title: '点击量 ', sort: true}
      ,{field:'type', width:100, title: '类型'}
      ,{field:'username', width:120, title: '所属用户', sort: true}
    ]]
    
  });
});
</script>
</body>
</html>