<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<title>添加图书</title>
</head>
<body>
	<%@ include file="top.jsp"%>
	<div id="container">
		<div class="box_top">
			<b class="pl15">当前位置：</b>新增图书
			<div id="home"><a href="book/${1 }/selectBook.do"><img src="2/img/skin_/home.png"></a></div>
		</div>
		<div class="box_center">
			<form action="book/insertBook.do" method="post" class="jqtransform" onsubmit="return validate()">
				<table class="form_table pt15 pb15" width="100%" border="0"
					cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td class="td_right">图书编号：</td>
							<td class=""><input type="text" name="id" id="id"
								class="input-text lh30" size="40" required>
								<div id="mess"></div>
							</td>
						</tr>
						
						<tr>
							<td class="td_right">图书名称：</td>
							<td class=""><input type="text" name="name"
								class="input-text lh30" size="40" required></td>
						</tr>
						<tr>
							<td class="td_right">图书作者：</td>
							<td class=""><input type="text" name="author"
								class="input-text lh30" size="40" required></td>
						</tr>
						<tr>
							<td class="td_right">出版社：</td>
							<td class=""><input type="text" name="publish"
								class="input-text lh30" size="40" required></td>
						</tr>
						<tr>
							<td class="td_right">出版时间：</td>
							<td class=""><input type="text" name="time"
								class="input-text lh30" size="40" required></td>
						</tr>
						<tr>
							<td class="td_right">价格：</td>
							<td class=""><input type="text" name="price"
								class="input-text lh30" size="40" required></td>
						</tr>
						<tr>
							<td class="td_right">&nbsp;</td>
							<td class="">
								<input type="submit" name="button" class="btn btn82 btn_save2"
								value="保存"> <input type="reset" name="button"
								class="btn btn82 btn_res" value="重置"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
	
	<script type="text/javascript">
	$(function(){
		//ajax图书编号校验
		$("#id").bind("keyup blur",function(){
			var bookId = $("#id").val();
			$.ajax({
				url:"book/insertToSelectBookById.do",
				type:"post",
				data:{"bookId":bookId},
				dataType:"text",
				success:function(result){
					if(result=='1'){
						$("#mess").html("图书编号已存在，请重新输入");
					}else{
						$("#mess").html("");
					}
				},
				error:function(){
					alert("请求异常！");
				}
			}); 
		});
		/* $("#id").keyup(function(){
			
		}); */
		
	});

	//表单字段为必填，警告div内容为空时才能提交
	function validate(){
		var mess = $("#mess").html();
		if(mess==""){
			return true;
		}else{
			return false;
		}
	}
</script>
</body>
</html>