<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/resource.jsp"%>
<link href="${ctx }/static/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/static/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/static/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${ctx }/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${ctx }/static/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${ctx }/static/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="${ctx }/static/lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx }/static/lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="${ctx }/static/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>

<div id="content">
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i>首页
		<span class="c-gray en">&gt;</span>图书管理
		<span class="c-gray en">&gt;</span>修改图书
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<form action="${ctx}/book/editBook.do" method="post" class="form form-horizontal" id="editBookForm">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>图书编号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" name="id" id="id" value="${book.id}">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>图书名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" name="name" value="${book.name}">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>图书作者：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" name="author" value="${book.author}">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>出版社：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" name="publish" value="${book.publish}">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>出版时间：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" autocomplete="off" class="input-text Wdate" name="time" value="${book.time}"
						   onfocus="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss'})">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>价格：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" name="price" value="${book.price}">
				</div>
			</div>

			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button id="editBookBtn" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 修改</button>
					<button id="back" onclick="javascript:$('#content').load('${ctx}/book/bookList.do');" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 返回</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
$(function () {
	//修改
	$("#editBookBtn").click(function() {
		var form = $("#editBookForm");
		form.ajaxSubmit({
			success: function (data) {
				if (data.success){
					$("#content").load("${ctx}/book/bookList.do");
				}
			},
			error : function() {
				layer.alert("请求失败", {icon : 2});
			}
		});
	});
});
</script>