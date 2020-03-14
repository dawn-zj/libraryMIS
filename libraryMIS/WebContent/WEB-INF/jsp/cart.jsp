<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<title>购物车</title>
</head>
<body>
	<%@ include file="top.jsp"%>	
	<div id="container">
		<div class="box_top">
			<b class="pl15">当前位置：</b>购物车
			<div id="home"><a href="book/${1 }/selectBook.do"><img src="2/img/skin_/home.png"></a></div>
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
							<th style="width: 30px;"><input type="checkbox"></th>
							<th style="width: 50px;">序号</th>
							<th style="width: 210px;">图书名称</th>
							<th style="width: 100px;">作者</th>
							<th style="width: 170px;">出版社</th>
							<th style="width: 100px;">出版时间</th>
							<th style="width: 80px;">价格</th>
							<th style="width: 100px;">数量</th>
							<th style="width: 80px;">合计</th>
							<th style="width: 60px;">操作</th>
						</tr>
						<c:forEach items="${cart}" var="book">

							<tr class="tr" style="background-color: rgb(255, 255, 255);">
								<td class="td_center"><input type="checkbox"></td>
								<td id="bookId">${book.value.id}</td>
								<td>${book.value.name}</td>
								<td>${book.value.author}</td>
								<td>${book.value.publish}</td>
								<td>${book.value.time}</td>
								<td>${book.value.price}</td>
								<td>
									<div class="item_quantity_editer" data-item-key="702031200_ht180825p4333774t1"> 
										<div class="decrease_one">-</div> 
										<input class="item_quantity" type="text" size="15px" value="${book.value.count}"> 
										<div class="increase_one">+</div> 
									</div>
								</td>
								<td>${book.value.price*book.value.count}</td>
								<td><a href="cart/${book.value.id}/deleteCartBook.do"
									class="ext_btn"> 删除</a> 
								</td>
							</tr>
						</c:forEach>
						<c:set var="groupPrice" value="0.00"></c:set>
						<tr><td colspan="10" id="group_price">商品金额：<span class="group_total_price">¥${groupPrice}</span>&nbsp;&nbsp;&nbsp;</td></tr>
					</tbody>
				</table>
			</div>
		</div>
		<div id="allPrice">
			<div class="right_handler"> 
				共 <span class="total_amount">  2 </span> &nbsp;件商品 &nbsp;&nbsp; 
				商品应付总额：<span class="total_price">¥${groupPrice}</span> 
				<a id="go_to_order" class="btn">去结算</a> 
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
	
	<script type="text/javascript">
	$(function(){
		//ajax购物车图书数量的添加_文本框
		$(".item_quantity").blur(function(){
			var bookCount = $(this).val();
			var price = $(this).parent().parent().prev("td").html();
			$(this).parent().parent().next("td").attr("name","flag");
			var bookId = $(this).parent().parent().parent().children("#bookId").html();
			$.ajax({
				url:"cart/inputCount.do",
				type:"post",
				data:{"bookCount":bookCount,"bookId":bookId},
				dataType:"text",
				success:function(result){
					if(result=='1'){
						/* window.location.href="cart/toAddCart.do"; */
						$("[name='flag']").html(price*bookCount);
						$("[name='flag']").removeAttr("name");
						
					}else{
						alert("添加数量失败！");
					}
				},
				error:function(){
					alert("请求异常！");
				}
			});
		});
		
		//减
		$(".decrease_one").click(function(){
			var bookCount = $(this).next().val();
			var price = $(this).parent().parent().prev("td").html();
			$(this).next().attr("name","flag_input");
			$(this).parent().parent().next("td").attr("name","flag_allprice");
			var bookId = $(this).parent().parent().parent().children("#bookId").html();
			$.ajax({
				url:"cart/decrease.do",
				type:"post",
				data:{"bookCount":bookCount,"bookId":bookId},
				dataType:"text",
				success:function(result){
					if(result!=null){
						/* window.location.href="cart/toAddCart.do"; */
						$("[name='flag_input']").val(result);
						$("[name='flag_allprice']").html(price*result);
						$("[name='flag_input']").removeAttr("name");
						$("[name='flag_allprice']").removeAttr("name");
						
					}else{
						alert("添加数量失败！");
					}
				},
				error:function(){
					alert("请求异常！");
				}
			});
		});
		
		/* 
		<td>${book.value.price}</td>
		<td>
			<div class="item_quantity_editer" data-item-key="702031200_ht180825p4333774t1"> 
				<div class="decrease_one">-</div> 
				<input class="item_quantity" type="text" size="15px" value="${book.value.count}"> 
				<div class="increase_one">+</div> 
			</div>
		</td>
		<td>${book.value.price*book.value.count}</td>
		
		 */
		
		//加
		$(".increase_one").click(function(){
			var bookCount = $(this).prev().val();
			var price = $(this).parent().parent().prev("td").html();
			$(this).prev().attr("name","flag_input");
			$(this).parent().parent().next("td").attr("name","flag_allprice");
			var bookId = $(this).parent().parent().parent().children("#bookId").html();
			$.ajax({
				url:"cart/increase.do",
				type:"post",
				data:{"bookCount":bookCount,"bookId":bookId},
				dataType:"text",
				success:function(result){
					if(result!=null){
						/* window.location.href="cart/toAddCart.do"; */
						$("[name='flag_input']").val(result);
						$("[name='flag_allprice']").html(price*result);
						$("[name='flag_input']").removeAttr("name");
						$("[name='flag_allprice']").removeAttr("name");
						
					}else{
						alert("添加数量失败！");
					}
				},
				error:function(){
					alert("请求异常！");
				}
			});
		});
		
	});
	</script>
</body>
</html>