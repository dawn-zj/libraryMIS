<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<title>修改图书</title>
</head>
<body>
	<%@ include file="top.jsp"%>
	<div id="container">
		<div class="box_top">
			<b class="pl15">当前位置：</b>修改图书
			<div id="home"><a href="book/${1 }/selectBook.do"><img src="2/img/skin_/home.png"></a></div>
		</div>
		<div class="box_center">
			<form action="book/updateBook.do" method="post" class="jqtransform">
<!-- 				 width="100%" border="0" cellpadding="0" cellspacing="0" -->
				<table class="form_table pt15 pb15">
					<tbody>
						<tr>
							<td class="td_right">图书编号：</td>
							<td class=""><input type="text" name="id" value="${book.id}"
								class="input-text lh30" size="40" readonly="readonly"></td>
						</tr>
						<tr>
							<td class="td_right">图书名称：</td>
							<td class=""><input type="text" name="name"  value="${book.name}"
								class="input-text lh30" size="40"></td>
						</tr>
						<tr>
							<td class="td_right">图书作者：</td>
							<td class=""><input type="text" name="author"  value="${book.author}"
								class="input-text lh30" size="40"></td>
						</tr>
						<tr>
							<td class="td_right">出版社：</td>
							<td class=""><input type="text" name="publish"  value="${book.publish}"
								class="input-text lh30" size="40"></td>
						</tr>
						<tr>
							<td class="td_right">出版时间：</td>
							<td class=""><input type="text" name="time"  value="${book.time}"
								class="input-text lh30" size="40"></td>
						</tr>
						<tr>
							<td class="td_right">价格：</td>
							<td class=""><input type="text" name="price"  value="${book.price}"
								class="input-text lh30" size="40"></td>
						</tr>
						<tr>
							<td class="td_right">&nbsp;</td>
							<td class="">
								<input type="submit" name="button" class="btn btn82 btn_save2"
								value="保存"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>