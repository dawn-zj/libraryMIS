<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="taglibs.jsp" %>
<link rel="stylesheet" href="${ctx }/css/style.css">
<link rel="stylesheet" href="${ctx }/css/myStyle.css">
<link rel="stylesheet" href="${ctx }/css/main.css">
<link rel="stylesheet" href="${ctx }/css/Hui-iconfont/1.0.8/iconfont.css">
<link rel="stylesheet" href="${ctx }/css/Hui-iconfont/Hui-body.css">

<div class="top navbar navbar-fixed-top">
	<div id="top_t" class="container-fluid cl">
		<div id="head_left"></div>
		<div id="logo" class="fl"></div>
		<div id="photo_info" class="fr">
			<div id="photo" class="fl">
				<a href="#"><img src="${ctx }/images/head.png" alt="" width="60"
					height="60"></a>
			</div>
			<div id="base_info" class="fr">
				<div class="help_info">
					<ul class="cl">
						<li class="dropDown right" ><a href="https://www.baidu.com/">帮助</a></li>
						<li class="dropDown right" ><a href="https://www.baidu.com/">关于</a></li>
						<li class="dropDown right" ><a href="https://www.baidu.com/">退出</a></li>
					</ul>
					<!-- <a href="https://www.baidu.com/" id="hp">帮助&nbsp;&nbsp;&nbsp;|</a>
					<a href="https://www.baidu.com/" id="gy">关于&nbsp;&nbsp;&nbsp;|</a>
					<a href="user/login.do" id="out">退出</a> -->
				</div>
			</div>
		</div>
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>超级管理员</li>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">${sessionScope.sysUser.account } <i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
						<li><a href="#">切换账户</a></li>
						<li><a href="#">退出</a></li>
					</ul>
				</li>
				<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<!-- <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li> -->
						<li><a href="javascript:;" data-val="default" title="默认（蓝色）">默认（蓝色）</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
