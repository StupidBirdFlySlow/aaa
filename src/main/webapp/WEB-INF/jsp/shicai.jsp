<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" media="all" href="https://s1.c.meishij.net/n/css/ss_base.css?v=1517" />
		<link rel="stylesheet" type="text/css" media="all" href="https://s1.c.meishij.net/n/css/main.css?v=1961" />
		<link rel="stylesheet" type="text/css" media="all" href="https://s1.c.meishij.net/n/css/shicai.css?v=1612" />
		<script type="text/javascript" src="https://s1.c.meishij.net/n/js/j.m.js?v=1551"></script>
		<script type="text/javascript" src="https://s1.c.meishij.net/n/js/main.js?v=1516"></script>
		<script type="text/javascript" src="https://s1.c.meishij.net/n/js/list.js"></script>
		<!-- <script src="https://dup.baidustatic.com/js/ds.js"></script> -->
	</head>

	<body style="">
	
		<!-- <div class="nav">
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
				<li>
					<a href="http://localhost/design/knowledge/index.html" class="nav_link"><strong>健康饮食知识</strong></a>
				</li>
				<li class="current">
					<a href="http://localhost/design/shicaibaike/index.html" class="nav_link"><strong>食材百科</strong></a>
				</li>
			</ul>
		</div> -->
		<div class="main_w clearfix">
			<div class="main">
				<ul class="pathstlye1">
					<li>
						<a href="/">首页</a>
					</li>
					<li> &gt; </li>
					<li>
						<a href="">菜谱大全</a>
					</li>
					<li> &gt; </li>
					<li>
						<a href="">食材百科</a>
					</li>
					<li> &gt; </li>
					<li>
						<h2>${entity.name }</h2></li>
				</ul>
				<div class="sc_header">
					<img class="sc_headerimg" alt="苹果" src="${entity.img_url }${entity.img_name}" />
					<div class="sc_header_con1">
						<h1>${entity.name }</h1>
						<p class="p1"></p>
						<p class="p2">${entity.baseInfo }</p>
					</div>
				</div>
				<h2 class="bbtitles">${entity.name }的做法大全</h2>
				<div class="shicai_maincon">
					<div class="shicai_cp clearfix">
						<div class="shicai_cp_w clearfix">
							<div class="sccon_left" id="sccon_left">
								<div class="listtyle1_w clearfix" id="listtyle1_w">
									<div class="listtyle1_list clearfix" id="listtyle1_list">
										<div class="scc_masker pngFix" style="display:none;"></div>
										<c:forEach items="${menuList }" var="item" varStatus="s">
											<div class="listtyle1 <c:if test="${s.count%3==1}">ml0</c:if>"><!--  -->
												<a target="_blank" href="" title="${item.cpname }" class="big">
													<img class="img" alt="${item.cpname }" src="${item.img_src }${item.img_name}">
													<div class="i_w">
														<div class="i" style="margin-top: 0px;">
															<div class="c1"><strong>${item.cpname }</strong><span>245 评论  411437 人气</span><em>杉妈苹果</em></div>
														</div>
													</div>
													<!-- <strong class="gx"><span>脂肪肝</span></strong>  -->
												</a>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
							<div class="sccon_right" id="sccon_right">
								${entity.content }
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</body>

</html>