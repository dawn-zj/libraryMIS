<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajax+div局部刷新</title>
<style type="text/css">

*{margin: 0px;padding:0px;}
#container{width:400px;height:400px;background-color: red;margin:80px auto;}
ul{width:400px;height:40px;background-color:gray;}
li{float:left;width:198px;border:1px solid white;line-height: 38px;text-align: center;
	list-style-type: none;cursor: pointer;
}
#content{width:400px;height:360px;background-color:yellow;}

</style>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		//ajax图书编号校验
		$(".1").click(function(){
			var classValue = $(this).attr("class");
			//alert(classValue);
			$.ajax({
				url:"nav/localRefresh.do",
				type:"post",
				data:{"classValue":classValue},
				dataType:"html",
				success:function(result){
					if(result!=null){
						//alert("返回结果："+result);
						$("#content").html(result);
						
					}else{
						alert("返回错误！");
					}
				},
				error:function(result){
					alert("错误"+result);
				}
			}); 
		});
	});
</script>
</head>
<body>
	<div id="container">
		<ul>
			<li class="1">慕容景</li>
			<li class="2">云浅月</li>
		</ul>
		<div id="content"></div>
	</div>
	
</body>
</html>