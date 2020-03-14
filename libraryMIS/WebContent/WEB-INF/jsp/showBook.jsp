<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ include file="head.jsp" %>	
<title>主页</title>
</head>
<body>
	<%@ include file="top.jsp" %>	
	<div id="container">
		<div class="box_top">
			<b class="pl15">当前位置：</b>主页
			<div id="home"><a href="book/${1 }/selectBook.do"><img src="2/img/skin_/home.png"></a></div>
		</div>
		<div class="box_center pt10 pb10">
		
		<%-- <c:set var="index" value="1"></c:set> --%>
		
			<form action="book/${1 }/selectBook.do" method="post">
				<table class="form_table" border="0" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td>图书名称</td>
							<td><input type="text" name="name" class="input-text lh25" size="20"></td>
							<td>作者</td>
							<td><input type="text" name="author" class="input-text lh25" size="20"></td>
							<td><input type="submit" name="submit" class="btn btn82 btn_search" value="查询"></td>
							<td class="myBtn">
								<a href="cart/toAddCart.do"><i id="mycart" class="icon Hui-iconfont"></i></a>
								<a href="book/toAddBook.do" class="ext_btn"><span class="add"></span>添加</a> 
								<input type="button" value="返回"
								onclick="location.href='javascript:history.go(-1)'"
								class="ext_btn">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div id="count">
			<%-- <c:if test="${empty totalCount || totalCount==null}">
				<c:set var="totalCount" value="0"></c:set>
			</c:if> --%>
			<c:choose>
				<c:when test=" ${page.totalCount==0 }">
					暂无记录
				</c:when>
				<c:otherwise>
					当前页：${page.pageIndex }/${page.totalPage } 共有  ${page.totalCount } 条记录
				</c:otherwise>
			</c:choose>
		</div>
		<div id="table" class="mt10">
			<div class="box span10 oh">
				<div class="CRC" style="width: 1122px;">
					<div class="CRG" style="left: 32px; height: 326px;">
						<div class="grip"></div>
						<div class="CRZ" style="cursor: e-resize"></div>
					</div>
					<div class="CRG" style="left: 133px; height: 326px;">
						<div class="grip"></div>
						<div class="CRZ" style="cursor: e-resize"></div>
					</div>
					<div class="CRG" style="left: 234px; height: 326px;">
						<div class="grip"></div>
						<div class="CRZ" style="cursor: e-resize"></div>
					</div>
					<div class="CRL" style="left: 1123px; height: 326px;"></div>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="list_table CRZ" id="CRZ0">
					<tbody>
						<tr>
							<th style="width: 30px;text-align: center;"><input type="checkbox" id="all_checked"></th>
							<th style="width: 50px;text-align: center;">序号</th>
							<th style="width: 250px;text-align: center;">图书名称</th>
							<th style="width: 100px;text-align: center;">作者</th>
							<th style="width: 220px;text-align: center;">出版社</th>
							<th style="width: 120px;text-align: center;">出版时间</th>
							<th style="width: 80px;text-align: center;">价格</th>
							<th style="width: 140px;text-align: center;">操作</th>
						</tr>
						<c:forEach items="${books}" var="book">

							<tr class="tr" style="background-color: rgb(255, 255, 255);">
								<td class="td_center"><input type="checkbox" id="sub_check" value="${book.id}"></td>
								<td>${book.id}</td>
								<td>${book.name}</td>
								<td>${book.author}</td>
								<td>${book.publish}</td>
								<td>${book.time}</td>
								<td>${book.price}</td>
								<td>
									<%-- <a href="book/${book.id}/deleteBook.do"
									class="ext_btn"> 删除</a>  --%>
									<a href="book/${book.id}/toUpdateBook.do"
									class="ext_btn"> 修改</a>
									<a href="cart/${book.id}/addCart.do"
									class="ext_btn"> 加购</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div id="delete"><a id="batch_delete">批量删除</a></div>
		
		<c:if test="${page.totalCount!=0 }">
		<c:set var="start" value="1"></c:set>
		<c:set var="end" value="1"></c:set>
		<div class="page mt10">
			<div class="pagination">
				<c:choose>
					<c:when test="${page.pageIndex==1 }">
						<ul>
							<li class="first-child"><a href="book/${1}/selectBook.do">首页</a></li>
							<li class="disabled"><span>上一页</span></li>
					</c:when>
					<c:otherwise>
						<ul>
							<li class="first-child"><a href="book/${1}/selectBook.do">首页</a></li>
							<li><a href="book/${page.pageIndex-1 }/selectBook.do">上一页</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page.totalPage<=5 }">
						<c:forEach var="i" begin="1" end="${page.totalPage }">
							<li class="first-child">
								<a href="book/${i }/selectBook.do"><c:out value="${i }"></c:out></a>
							</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${page.pageIndex<3 }">
								<c:forEach var="i" begin="1" end="5">
									<li class="first-child">
										<a href="book/${i }/selectBook.do"><c:out value="${i }"></c:out></a>
									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:set var="start" value="${page.pageIndex-1 }"></c:set>
								<c:set var="end" value="${page.pageIndex+3 }"></c:set>
								<c:choose>
									<c:when test="${end>page.totalPage }">
										<c:forEach var="i" begin="${page.totalPage-4 }" end="${page.totalPage }">
											<li class="first-child">
												<a href="book/${i }/selectBook.do"><c:out value="${i }"></c:out></a>
											</li>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach var="i" begin="${start }" end="${end}">
											<li class="first-child">
												<a href="book/${i }/selectBook.do"><c:out value="${i }"></c:out></a>
											</li>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${page.pageIndex==page.totalPage }">
							<li class="disabled"><span>下一页</span></li>
							<li><a href="book/${page.totalPage }/selectBook.do">末页</a></li>
						</ul>
					</c:when>
					<c:otherwise>
							<li><a href="book/${page.pageIndex+1 }/selectBook.do">下一页</a></li>
							<li class="last-child"><a href="book/${page.totalPage }/selectBook.do">末页</a></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		</c:if>
	</div>
	<%@ include file="footer.jsp" %>
	
	
<script type="text/javascript">
	$(function(){
		//全选
		$("#all_checked").click(function(){
			if($(this).attr("checked")){
				$("input:checkbox").attr("checked",true);
			}else{
				$("input:checkbox").attr("checked",false);
			}
		});
		//批量删除
		$("#batch_delete").click(function(){
			var checkedNum= $("#sub_check:checked").length;
			//alert(checkedNum);
			if(checkedNum==0){
				alert("请至少选择一条数据");
			}else{
				var arr = new Array();
				$("#sub_check:checked").each(function(){
					var id = $(this).val();
					arr.push(id);
				});
				
				//ajax批量删除
				$.ajax({
					url:"book/batchDeleteBooks.do",
					type:"post",
					data:{"bookIds":arr.toString()},
					dataType:"text",
					success:function(result){
						if(result==1){
							//删除当前tr，避免页面跳转	但是当前页 和 总记录等不会刷新  有待完善
							//$("#sub_check:checked").parent().parent().remove();
							
							//但是页面跳转会导致没有book对象，无法分页  有待完善
							window.location.href="book/${1}/selectBook.do";
							
						}else{
							alert("删除失败！");
						}
					},
					error:function(){
						/* alert("请求错误！"); */
					}
				});
			}
		});
	});
</script>
</body>
</html>
