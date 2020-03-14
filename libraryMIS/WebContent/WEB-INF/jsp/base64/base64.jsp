<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/resource.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="${ctx }/layui/css/layui.css">
<script type="text/javascript" src="${ctx }/layui/layui.js"></script> 

<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i>首页
	<span class="c-gray en">&gt;</span>工具管理
	<span class="c-gray en">&gt;</span>base64编解码
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>

<div>
	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		<ul id="tabUl" class="layui-tab-title">
			<shiro:hasPermission name="base64:toBase64Encode">
				<li id="li1" class="layui-this" onclick="showTab('tab1','${ctx}/base64/toBase64Encode.do')">base64编码</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="base64:toBase64Decode">
				<li id="li2" onclick="showTab('tab2','${ctx}/base64/toBase64Decode.do')">base64解码</li>
			</shiro:hasPermission>
			
		</ul>
		<div id="tabContent" class="layui-tab-content">
			
			<shiro:hasPermission name="base64:toBase64Encode">
				<div id="tab1" class="layui-tab-item layui-show"></div>
			</shiro:hasPermission>
			<shiro:hasPermission name="base64:toBase64Decode">
				<div id="tab2" class="layui-tab-item"></div>
			</shiro:hasPermission>
			
			
		</div>
	</div>


</div>
<script type="text/javascript">
$(function(){
	//tab选项卡
	layui.use('element', function(){
		var element = layui.element;
	});
	
	//激活第一个选项卡，并发请求
	var firstLi = $("#tabUl li").first();
	firstLi.addClass(" layui-this");
	$("#tabContent div").first().addClass(" layui-show");
	var id = firstLi.attr("id");
	if(id == "li1"){
		showTab('tab1','${ctx}/base64/toBase64Encode.do')
	}else if(id == "li2"){
		showTab('tab2','${ctx}/base64/toBase64Decode.do');
	}
	
});
</script>
