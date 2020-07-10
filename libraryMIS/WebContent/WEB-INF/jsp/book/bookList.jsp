<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="../common/resource.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="${ctx }/static/lib/laypage/1.2/skin/laypage.css" />
<script type="text/javascript" src="${ctx }/static/lib/laypage/1.2/laypage.js"></script> 

<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i>首页
	<span class="c-gray en">&gt;</span>图书管理 
	<span class="c-gray en">&gt;</span>图书管理
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>

<div class="page-container">
	<div class="text-l">
		图书名称：<input type="text" name="name" value="${book.name }" id="name" placeholder="图书名称" style="width:180px" class="input-text radius">
		作者：<input type="text" name="author" value="${book.author }" id="author" placeholder="作者" style="width:180px" class="input-text radius">
		<button id="searchBook" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜图书</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<shiro:hasPermission name="book:toAdd">
				<a class="btn btn-primary radius" data-title="添加图书" data-href="${ctx }/book/toAddBook.do" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i>添加图书</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="book:toUpdate">
				<button class="btn btn-primary radius" data-title="修改图书" id="editBookBtn">修改图书</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="book:delete">
				<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> 
			</shiro:hasPermission>
		</span>
		<span class="r">共有数据：<strong>54</strong> 条</span>
	</div>
	
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th style="width: 30px;text-align: center;"><input type="checkbox" id="all_checked"></th>
					<th style="width: 50px;text-align: center;">序号</th>
					<th style="width: 250px;text-align: center;">图书名称</th>
					<th style="width: 100px;text-align: center;">作者</th>
					<th style="width: 220px;text-align: center;">出版社</th>
					<th style="width: 120px;text-align: center;">出版时间</th>
					<th style="width: 80px;text-align: center;">价格</th>
					<th style="width: 140px;text-align: center;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.result}" var="book">
					<tr class="text-c" style="background-color: rgb(255, 255, 255);">
						<td><input type="checkbox" id="sub_check" name="checkboxt" value="${book.id}"></td>
						<td>${book.id}</td>
						<td>${book.name}</td>
						<td>${book.author}</td>
						<td>${book.publish}</td>
						<td>${book.time}</td>
						<td>${book.price}</td>
						<td>
							<a style="text-decoration:none" href="${ctx }/book/${book.id}/deleteBook.do" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a> 
							<a style="text-decoration:none" class="ml-5" href="${ctx }/book/${book.id}/toUpdateBook.do" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> 
							<a style="text-decoration:none" class="ml-5" href="${ctx }/cart/${book.id}/addCart.do" title="加购">加购</a> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="text-r" id="page"></div>
	</div>
</div>

<script type="text/javascript">
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
					window.location.replace("${ctx }/book/bookList.do?pageNo=" + e.curr);
				}
			}
		});
	});
	$(function(){
		//搜索图书
		$("#searchBook").click(function(){
			var param = "?name="+$("#name").val();
			param = param + "&author="+$("#author").val();
			window.location.replace("${ctx}/book/bookList.do" + param);
		});

		// 修改图书
		$("#editBookBtn").click(function() {
			var index = 0;
			$("[name=checkboxt]:checkbox").each(
				function() {
					if (this.checked) {
						if (index == 0) {
							id = $(this).val();
						}
						index++;
					}
				});

			if (index == 0 || index>1) {
				layer.alert("请选择要修改的一条记录",{icon:0});
				return;
			} else {
				window.location.replace("${ctx }/book/toEditBook.do?id="+id);
			}
		});
	});
</script>
