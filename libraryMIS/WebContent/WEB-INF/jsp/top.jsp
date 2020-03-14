<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="top">
	<div id="top_t">
		<div id="head_left"></div>
		<div id="logo" class="fl"></div>
		<div id="photo_info" class="fr">
			<div id="photo" class="fl">
				<a href="#"><img src="images/head.png" alt="" width="60"
					height="60"></a>
			</div>
			<div id="base_info" class="fr">
				<div class="help_info">
					<div id="help"></div>
					<a href="https://www.baidu.com/" id="hp">帮助&nbsp;&nbsp;&nbsp;|</a>
					<a href="https://www.baidu.com/" id="gy">关于&nbsp;&nbsp;&nbsp;|</a>
					<a href="user/login.do" id="out">退出</a>
				</div>
				<div class="info_center">

					<ul class="bs-glyphicons-list" id="ul1">
						<li id="li1"><span class="glyphicon glyphicon-user"></span>
							<%=session.getAttribute("username")%>
							<i class="Hui-iconfont"></i>
							<ul class="dropDown-menu menu radius box-shadow" id="ul1-1">
								<li class="">个人信息</li>
								<li class="">切换账户</li>
							</ul>
						</li>
						<li id="li2"> <i class="icon Hui-iconfont"></i>
							<ul id="ul1-2">
								<li id="blue">蓝色（默认）</li>
								<li id="gray">灰色</li>
							</ul>
						</li>

					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#li1").hover(function(){
			$(this).css("background-color","#1E90FF").css("cursor","pointer");
			$("#ul1-1").show();
		},function(){
			$(this).css("background-color","rgba(0,0,0,0)");
			$("#ul1-1").hide();
		});
		
		$("#li2").hover(function(){
			$(this).css("background-color","#1E90FF").css("cursor","pointer");
			$("#ul1-2").show();
		},function(){
			$(this).css("background-color","rgba(0,0,0,0)");
			$("#ul1-2").hide();
		});
		
		//换肤
		$("#blue").click(function(){
			$(".top").css("background-image","url('img/bg-blue.PNG')");
			$("#head_left").css("background-position","0px -90px");
		});
		$("#gray").click(function(){
			$(".top").css("background-image","url('img/bg-gray.PNG')");
			$("#head_left").css("background-position","0px 90px");
		});
	});
</script>