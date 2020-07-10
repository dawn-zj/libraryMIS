<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="../common/resource.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/static/lib/laypage/1.2/skin/laypage.css" />
<script type="text/javascript" src="${ctx }/static/lib/laypage/1.2/laypage.js"></script> 

<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i>首页
	<span class="c-gray en">&gt;</span>日志管理
	<span class="c-gray en">&gt;</span>错误日志
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>

<div class="page-container">
	
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th style="width: 50px;text-align: center;">文件名</th>
					<th style="width: 250px;text-align: center;">修改时间</th>
					<th style="width: 100px;text-align: center;">大小(KB)</th>
					<th style="width: 140px;text-align: center;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.result}" var="log">
					<tr class="text-c" style="background-color: rgb(255, 255, 255);">
						<td>${log.fileName}</td>
						<td>${log.fileTimeCn}</td>
						<td>${log.fileSize}</td>
						<td>
							<a style="text-decoration:none" href="javascript:viewLog('${log.fileName }')" title="查看">查看</a>
							<a style="text-decoration:none" class="ml-5" href="${ctx }/log/downloadFile.do?fileName=${log.fileName }" title="下载">下载</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="text-r" id="errorLogFilePage"></div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		//===分页===
		<%--page('errorLogFilePage', '${page.totalPage}', '${page.pageNo}',"${ctx }/log/errorLogList.do?pageNo=");--%>
	});

	$(function(){
		laypage({
			cont : 'page',
			skip : true,//跳转页面选项
			pages : '${page.totalPage}',
			curr : function() {
				var pageNo = '${page.pageNo}';
				return pageNo ? pageNo : 1;
			}(),
			jump : function(e, first) {
				if (!first) {
					window.location.replace("${ctx }/log/errorLogList.do?pageNo=" + e.curr);
				}
			}
		});
	});
	function viewLog(fileName){
		window.location.replace("${ctx }/log/viewFile.do?fileName=" + fileName);
	}

</script>
