<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/resource.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/layui/css/layui.css">
<script type="text/javascript" src="${ctx }/layui/layui.js"></script> 

<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i>首页
	<span class="c-gray en">&gt;</span>工具管理
	<span class="c-gray en">&gt;</span>在线二维码
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>

<div>
	<form action="" method="post" class="form form-horizontal" id="form-article-add">
		<div class="text-l">
			内容：<input type="text" name="content" id="content" placeholder="内容" style="width:180px" class="input-text radius">
			<button id="genImage" class="btn btn-primary radius" type="button">生成二维码</button>
		</div>
	</form>
	<div id="barcodeImg">
		<img src="">
	</div>
</div>
<script type="text/javascript">
$(function(){
	$("#genImage").click(function(){
		var content = $("#content").val();
		if(content==""){
			return false;
		}
		$("#barcodeImg img").attr("src","${ctx}/barcode/genImage.do?content="+content);
	});
});
$(function(){
	//tab选项卡
	layui.use('element', function(){
		var element = layui.element;
	});
});
</script>
