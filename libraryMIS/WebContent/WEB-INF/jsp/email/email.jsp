<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/resource.jsp"%>

<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i>首页
	<span class="c-gray en">&gt;</span>工具管理
	<span class="c-gray en">&gt;</span>测试邮件
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>

<div class="page-container">
	<form action="" method="post" class="form form-horizontal" id="emailForm">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>收件人：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="" name="receiveMailAccount">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>主题：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="" name="subject">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>正文：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" id="" name="content">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button id="sendEmail" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 发送</button>
				<button onClick="" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
$(function(){
	//发送邮件
	$("#sendEmail").click(function(){
		var formJson = $("#emailForm").serialize();
		$.ajax({
			url:"${ctx}/email/sendEmail.do",
			type:"post",
			data:formJson,
			success:function(data){
				if(data == "ok"){
					alert("发送成功");
					window.location.replace("${ctx}/email/emailManage.do");
				}else{
					alert("发送失败");
				}
			},
			error:function(){
				alert("请求异常！");
			}
		}); 
	});
});
</script>
