<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="../common/resource.jsp"%>
<script type="text/javascript" src="${ctx }/js/jscolor.js"></script>

<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i>首页
	<span class="c-gray en">&gt;</span>工具管理
	<span class="c-gray en">&gt;</span>测试邮件
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>

<div class="page-container">
	<form action="" method="post" class="form form-horizontal" id="emailForm">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>HEX：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input id="chosen-value" type="text" class="input-text radius" value="FF0000">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>RGB：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input id="rgb" type="text" class="input-text radius" value="255,0,0">
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>Example 1：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="jscolor input-text radius"  value="FF0000">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>Example 2：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<button class="btn btn-primary radius jscolor {valueElement:'chosen-value', onFineChange:'setTextColor(this)'}">
					Pick text color
				</button>
			</div>
		</div>
	</form>

	<script>
		function setTextColor(picker) {
			document.getElementsByTagName('body')[0].style.color = '#' + picker.toString();

			var rgbArray = picker.rgb;
			var rgbStr = Math.floor(rgbArray[0]) + "," + Math.floor(rgbArray[1]) + "," + Math.floor(rgbArray[2]);
			document.getElementById('rgb').value=rgbStr;
		}
	</script>

</div>
