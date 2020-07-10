<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="../common/resource.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/static/lib/laypage/1.2/skin/laypage.css" />
<script type="text/javascript" src="${ctx }/static/lib/laypage/1.2/laypage.js"></script> 

<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i>首页
	<span class="c-gray en">&gt;</span>日志管理
	<span class="c-gray en">&gt;</span>查看日志
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>

<div class="page-container">
	
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<tbody>
				<c:forEach items="${page.result}" var="logInfo">
					<tr class="text-l" style="background-color: rgb(255, 255, 255);">
						<td>${logInfo}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="text-r" id="page"></div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		//===分页===
		<%--page('page', '${page.totalPage}', '${page.pageNo}',"${ctx }/log/viewFile.do?fileName=${fileName}&pageNo=");--%>


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
					window.location.replace("${ctx }/log/viewFile.do?fileName=${fileName}&pageNo=" + e.curr);
				}
			}
		});
	});

</script>
