<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/loginResource.jsp" %>
<!DOCTYPE html>
<!-- 登录采用OA系统 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>

<body>
<p>${message }</p>
<div id="container" style="height: 445px;">
    <div id="bd" style="padding-top: 67px;">
    	<form action="${ctx}/sysUser/login.do" method="post">
    	<div id="main">
        	<div class="login-box">
                <div id="logo"></div>
                <h1></h1>
                
                <div class="input username" id="username">
                    <label for="userName">用户名</label>
                    <span></span>
                    <input type="text" id="account" name="account">
                </div>
                <div class="input psw" id="psw">
                    <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                    <span></span>
                    <input type="password" id="password" name="password">
                </div>
                <div class="input validate" id="validate">
                    <label for="valiDate">验证码</label>
                    <input type="text" id="valiDate">
                    <div class="value">X3D5</div>
                </div>
                <div class="styleArea">
                    <div class="styleWrap">
                        <div id="_iSelWrap_iSel-7365" class="iselect-wrapper"><select name="style" id="iSel-7365" class="iselect">
                            <option value="默认风格">默认风格</option>
                            <option value="红色风格">红色风格</option>
                            <option value="绿色风格">绿色风格</option>
                        </select><span id="_iSelVal_iSel-7365" class="iselwrap-val">默认风格</span></div>
                    </div>
                </div>
                <div id="btn" class="loginButton">
                   		<input type="submit" class="button" value="登录">
                </div>
            </div>
        </div>
        </form>
        <div id="ft">CopyRight&nbsp;2018&nbsp;&nbsp;版权所有&nbsp;&nbsp;爱吃鱼的简大Boss&nbsp;&nbsp;</div>
    </div>
</div>
<script type="text/javascript">
	var height = $(window).height() > 445 ? $(window).height() : 445;
	$("#container").height(height);
	var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
	$('#bd').css('padding-top', bdheight);
	$(window).resize(function(e) {
        var height = $(window).height() > 445 ? $(window).height() : 445;
		$("#container").height(height);
		var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
		$('#bd').css('padding-top', bdheight);
    });
	$('select').select();
	
	//登录页面,禁止浏览器的后退按钮
	$(function(){
		if(window.history && window.history.pushState){
			$(window).on('popstate',function(){
				window.history.pushState('forward', null, 'login.jsp');
				window.history.forward(1);
			});
		}
		window.history.pushState('forward', null, 'login.jsp');
		window.history.forward(1);
	});
</script>
</body>
</html>