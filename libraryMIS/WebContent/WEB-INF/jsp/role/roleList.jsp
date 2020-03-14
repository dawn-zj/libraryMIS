<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/resource.jsp"%>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i>首页
	<span class="c-gray en">&gt;</span>用户管理 
	<span class="c-gray en">&gt;</span>角色管理
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>

<div class="page-container">
	<div class="text-l">
<%-- 		图书名称：<input type="text" name="name" value="${book.name }" id="name" placeholder="图书名称" style="width:180px" class="input-text radius"> --%>
<%-- 		作者：<input type="text" name="author" value="${book.author }" id="author" placeholder="作者" style="width:180px" class="input-text radius"> --%>
<!-- 		<button id="searchBook" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜图书</button> -->
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-5">
		<span class="l">
<!-- 			<shiro:hasPermission name="book:toAdd"> -->
<%-- 				<a class="btn btn-primary radius" data-title="添加图书" data-href="${ctx }/book/toAddBook.do" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i>添加图书</a> --%>
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="book:toUpdate"> -->
<%-- 				<a class="btn btn-primary radius" data-title="修改图书" data-href="${ctx }/book/toAddBook.do" onclick="Hui_admin_tab(this)" href="javascript:;">修改图书</a> --%>
<!-- 			</shiro:hasPermission> -->
<!-- 			<shiro:hasPermission name="book:delete"> -->
<!-- 				<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>  -->
<!-- 			</shiro:hasPermission> -->
		</span>
		<span class="r">共有数据：<strong>54</strong> 条</span>
	</div>
	
	<div class="mt-10">
		<span class="text-c">共有数据：<strong>54</strong> 条</span>
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th style="width: 30px;"><input type="checkbox" id="all_checked"></th>
					<th>角色名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="role">
					<tr class="text-c">
						<td><input type="checkbox" value="${role.id}"></td>
						<td>${role.name}</td>
						<td>
							<a href="${ctx }/role/toEditRoleMenu.do?id=${role.id }" title="授权">授权</a> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>