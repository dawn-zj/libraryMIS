<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/resource.jsp"%>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i>首页
	<span class="c-gray en">&gt;</span>用户管理 
	<span class="c-gray en">&gt;</span>角色授权
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>

<div class="page-container">
	
	<div class="mt-20">
		
		<form id="editRoleMenuForm" class="form-horizontal"
			action="${ctx }/role/editRoleMenu.do?id=${role.id }"
			method="post">
			<div class="text-center">
				<label class="top-btn form-control-static">${role.name }</label>
			</div>

			<div id="table1" style="">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr  class="text-c">
							<th width="20%">主菜单</th>
							<th width="20%">子菜单</th>
							<th width="20%">页面菜单</th>
							<th width="40%">按钮菜单</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="firstMenu" items="${firstMenu}" varStatus="status">
							<c:choose>
								<c:when test="${firstMenu.sealMac == true}">
									<!-- 1行4列，均输出第一个 -->
									<tr style="height:40px" class="${status.count }">
										<td rowspan="${firstMenu.grandsonLength }">
											<label class="checkbox-inline"> 
												<input style="padding-top: 3px;" type="checkbox" class="firstMenu" name="menuIds" ${firstMenu.checked ? 'checked': '' } value="${firstMenu.id}">
												<span>${firstMenu.name }</span>
											</label>
										</td>
										<c:forEach var="secondMenu" items="${firstMenu.child}" begin="0" end ="0">
											<td rowspan="${secondMenu.childLength }" class="m0">
												<label class="checkbox-inline"> 
													<input style="padding-top: 3px;" type="checkbox" class="secondMenu" name="menuIds" ${secondMenu.checked ? 'checked': '' } value="${secondMenu.id}">
													<span>${secondMenu.name }</span>
												</label>
											</td>
											
											<c:forEach var="thirdMenu" items="${secondMenu.child}" begin="0" end ="0">
												<td class="m0">
													<label class="checkbox-inline"> 
														<input style="padding-top: 3px;" type="checkbox" class="thirdMenu" name="menuIds" ${thirdMenu.checked ? 'checked': '' } value="${thirdMenu.id}">
														<span>${thirdMenu.name }</span>
													</label>
												</td>
												<td class="m0">
													<c:forEach var="buttonMenu" items="${thirdMenu.button}">
														<label class="checkbox-inline"> 
															<input style="padding-top: 3px;" type="checkbox" name="buttonIds" ${buttonMenu.checked ? 'checked': '' } value="${buttonMenu.id }">
															<span>${buttonMenu.name }</span>
														</label>
													</c:forEach>
												</td>
											</c:forEach>
										</c:forEach>
									</tr>
									
									<c:if test="${firstMenu.grandsonLength > 1 }">
										<c:if test="${firstMenu.child[0].childLength > 1 }">
											<c:forEach var="thirdMenu" items="${firstMenu.child[0].child}" begin="1" end="${firstMenu.child[0].childLength }">
												<tr  class="${status.count }">
													<td class="m0">
														<label class="checkbox-inline"> 
															<input style="padding-top: 3px;" type="checkbox" class="thirdMenu" name="menuIds" ${thirdMenu.checked ? 'checked': '' } value="${thirdMenu.id}">
															<span>${thirdMenu.name }</span>
														</label>
													</td>
													<td class="m0">
														<c:forEach var="buttonMenu" items="${thirdMenu.button}">
															<label class="checkbox-inline"> 
																<input style="padding-top: 3px;" type="checkbox" name="buttonIds" ${buttonMenu.checked ? 'checked': '' } value="${buttonMenu.id}">
																<span>${buttonMenu.name }</span>
															</label>
														</c:forEach>
													</td>
												</tr>
											</c:forEach>
										</c:if>
										<!-- 二级行数+三级行数-->
										<c:forEach var="secondMenu" items="${firstMenu.child}" begin="1" end ="${firstMenu.childLength }" varStatus="secondStatus">
											<tr class="${status.count }">
												<td rowspan="${secondMenu.childLength }" class="m${secondStatus.count }">
													<label class="checkbox-inline"> 
														<input style="padding-top: 3px;" type="checkbox" class="secondMenu" name="menuIds" ${secondMenu.checked ? 'checked': '' } value="${secondMenu.id}">
														<span>${secondMenu.name }</span>
													</label>
												</td>
												
												<c:forEach var="thirdMenu" items="${secondMenu.child}" begin="0" end ="0">
													<td class="m${secondStatus.count }">
														<label class="checkbox-inline"> 
															<input style="padding-top: 3px;" type="checkbox" class="thirdMenu" name="menuIds" ${thirdMenu.checked ? 'checked': '' } value="${thirdMenu.id}">
															<span>${thirdMenu.name }</span>
														</label>
													</td>
													<td class="m${secondStatus.count }">
														<c:forEach var="buttonMenu" items="${thirdMenu.button}">
															<label class="checkbox-inline"> 
																<input style="padding-top: 3px;" type="checkbox" name="buttonIds" ${buttonMenu.checked ? 'checked': '' } value="${buttonMenu.id}">
																<span>${buttonMenu.name }</span>
															</label>
														</c:forEach>
													</td>
												</c:forEach>
											</tr>
											<c:if test="${secondMenu.childLength > 1 }">
												<c:forEach var="thirdMenu" items="${secondMenu.child}" begin="1" end="${secondMenu.childLength }">
													<tr class="${status.count }">
														<td class="m${secondStatus.count }">
															<label class="checkbox-inline"> 
																<input style="padding-top: 3px;" type="checkbox" class="thirdMenu" name="menuIds" ${thirdMenu.checked ? 'checked': '' } value="${thirdMenu.id}">
																<span>${thirdMenu.name }</span>
															</label>
														</td>
														<td class="m${secondStatus.count }">
															<c:forEach var="buttonMenu" items="${thirdMenu.button}">
																<label class="checkbox-inline"> 
																	<input style="padding-top: 3px;" type="checkbox" name="buttonIds" ${buttonMenu.checked ? 'checked': '' } value="${buttonMenu.id}">
																	<span>${buttonMenu.name }</span>
																</label>
															</c:forEach>
														</td>
													</tr>
												</c:forEach>
											</c:if>
										</c:forEach>
									</c:if>
									
								</c:when>
								<c:when test="${firstMenu.sealMac == false}">
									<!-- 1行4列，均输出第一个 -->
									<tr style="height:40px" class="${status.count }">
										<td rowspan="${firstMenu.grandsonLength }">
											<label class="checkbox-inline"> 
												<input style="padding-top: 3px;" type="checkbox" class="firstMenu" name="menuIds" ${firstMenu.checked ? 'checked': '' } value="${firstMenu.id}">
												<span class="errorMac">${firstMenu.name }</span>
											</label>
										</td>
										<c:forEach var="secondMenu" items="${firstMenu.child}" begin="0" end ="0">
											<td rowspan="${secondMenu.childLength }" class="m0">
												<label class="checkbox-inline"> 
													<input style="padding-top: 3px;" type="checkbox" class="secondMenu" name="menuIds" ${secondMenu.checked ? 'checked': '' } value="${secondMenu.id}">
													<span class="errorMac">${secondMenu.name }</span>
												</label>
											</td>
											
											<c:forEach var="thirdMenu" items="${secondMenu.child}" begin="0" end ="0">
												<td class="m0">
													<label class="checkbox-inline"> 
														<input style="padding-top: 3px;" type="checkbox" class="thirdMenu" name="menuIds" ${thirdMenu.checked ? 'checked': '' } value="${thirdMenu.id}">
														<span class="errorMac">${thirdMenu.name }</span>
													</label>
												</td>
												<td class="m0">
													<c:forEach var="buttonMenu" items="${thirdMenu.button}">
														<label class="checkbox-inline"> 
															<input style="padding-top: 3px;" type="checkbox" name="buttonIds" ${buttonMenu.checked ? 'checked': '' } value="${buttonMenu.id }">
															<span class="errorMac">${buttonMenu.name }</span>
														</label>
													</c:forEach>
												</td>
											</c:forEach>
										</c:forEach>
									</tr>
									
									<c:if test="${firstMenu.grandsonLength > 1 }">
										<c:if test="${firstMenu.child[0].childLength > 1 }">
											<c:forEach var="thirdMenu" items="${firstMenu.child[0].child}" begin="1" end="${firstMenu.child[0].childLength }">
												<tr  class="${status.count }">
													<td class="m0">
														<label class="checkbox-inline"> 
															<input style="padding-top: 3px;" type="checkbox" class="thirdMenu" name="menuIds" ${thirdMenu.checked ? 'checked': '' } value="${thirdMenu.id}">
															<span class="errorMac">${thirdMenu.name }</span>
														</label>
													</td>
													<td class="m0">
														<c:forEach var="buttonMenu" items="${thirdMenu.button}">
															<label class="checkbox-inline"> 
																<input style="padding-top: 3px;" type="checkbox" name="buttonIds" ${buttonMenu.checked ? 'checked': '' } value="${buttonMenu.id}">
																<span class="errorMac">${buttonMenu.name }</span>
															</label>
														</c:forEach>
													</td>
												</tr>
											</c:forEach>
										</c:if>
										<!-- 二级行数+三级行数-->
										<c:forEach var="secondMenu" items="${firstMenu.child}" begin="1" end ="${firstMenu.childLength }" varStatus="secondStatus">
											<tr class="${status.count }">
												<td rowspan="${secondMenu.childLength }" class="m${secondStatus.count }">
													<label class="checkbox-inline"> 
														<input style="padding-top: 3px;" type="checkbox" class="secondMenu" name="menuIds" ${secondMenu.checked ? 'checked': '' } value="${secondMenu.id}">
														<span class="errorMac">${secondMenu.name }</span>
													</label>
												</td>
												
												<c:forEach var="thirdMenu" items="${secondMenu.child}" begin="0" end ="0">
													<td class="m${secondStatus.count }">
														<label class="checkbox-inline"> 
															<input style="padding-top: 3px;" type="checkbox" class="thirdMenu" name="menuIds" ${thirdMenu.checked ? 'checked': '' } value="${thirdMenu.id}">
															<span class="errorMac">${thirdMenu.name }</span>
														</label>
													</td>
													<td class="m${secondStatus.count }">
														<c:forEach var="buttonMenu" items="${thirdMenu.button}">
															<label class="checkbox-inline"> 
																<input style="padding-top: 3px;" type="checkbox" name="buttonIds" ${buttonMenu.checked ? 'checked': '' } value="${buttonMenu.id}">
																<span class="errorMac">${buttonMenu.name }</span>
															</label>
														</c:forEach>
													</td>
												</c:forEach>
											</tr>
											<c:if test="${secondMenu.childLength > 1 }">
												<c:forEach var="thirdMenu" items="${secondMenu.child}" begin="1" end="${secondMenu.childLength }">
													<tr class="${status.count }">
														<td class="m${secondStatus.count }">
															<label class="checkbox-inline"> 
																<input style="padding-top: 3px;" type="checkbox" class="thirdMenu" name="menuIds" ${thirdMenu.checked ? 'checked': '' } value="${thirdMenu.id}">
																<span class="errorMac">${thirdMenu.name }</span>
															</label>
														</td>
														<td class="m${secondStatus.count }">
															<c:forEach var="buttonMenu" items="${thirdMenu.button}">
																<label class="checkbox-inline"> 
																	<input style="padding-top: 3px;" type="checkbox" name="buttonIds" ${buttonMenu.checked ? 'checked': '' } value="${buttonMenu.id}">
																	<span class="errorMac">${buttonMenu.name }</span>
																</label>
															</c:forEach>
														</td>
													</tr>
												</c:forEach>
											</c:if>
										</c:forEach>
									</c:if>
								</c:when>
							</c:choose>
						</c:forEach>
					</tbody>
				</table>
				<div class="form-actions mt-10">
					<div class="row">
						<div class="col-xs-offset-3 col-xs-6">
							<button id="reset" type="reset" class="btn btn-primary col-md-offset-2 col-xs-offset-2">重置</button>
							<button id="submitRoleMenu" class="btn btn-primary col-md-offset-2 col-xs-offset-2" type="button">确定</button>
							<button id="back" type="button" class="btn btn-primary col-md-offset-2 col-xs-offset-2" id="return">返回</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>










<script type="text/javascript">
$(function(){
	//授权
	 $("#submitRoleMenu").click(function(){		
		 var form=$("#editRoleMenuForm");
		 form.ajaxSubmit({
			 success:function(data){
			     if(data.success){
			    	 layer.alert(data.message,{icon:1});
			    	 window.location.replace("${ctx }/role/roleList.do");
			    	 parent.location.reload();
			      } else {
			    	  layer.alert(data.message,{icon:2});
			      } 
			    },error:function(){
			    	layer.alert("请求失败",{icon:2});
			    }
		 });
	});
	
	$(".firstMenu,.secondMenu").click(function(){
		/* 点击主菜单的input复选框，就选中子菜单以及所有button 	父：label；父父：td;父父父：tr*/
		var tr = $(this).parent().parent().parent();
		var index = $(this).parent().parent().attr("rowspan");
		var firstStatus = $(this).prop("checked");
		for(var i = 0; i < index;i++){
			$.each(tr.find("input"), function(i,e) {
				e.checked = firstStatus;
			});
			tr = tr.next();
		}
	});
	
	//点击子菜单：选上主菜单、三级菜单和所有button
	$(".secondMenu").click(function(){
		var tr = $(this).parent().parent().parent();
		var classIndex = tr.attr("class");
		var secondStatus = $(this).parent().parent().find("input").first()[0].checked;
		
		//选主菜单
		var cks= $("."+classIndex).find("input");
		$.each(cks,function(i,e){
			if(e.checked){
				cks[0].checked=true;
			}
		});
		
		//主菜单下没有被选的，主菜单也取消选择
		var checked = $("."+classIndex).find("input:checked");
		if(checked.length == 1){
			cks[0].checked=false;
		}
	});
	
	//点击三级菜单：选上主菜单、二级菜单和所有button
	$(".thirdMenu").click(function(){
		var tr = $(this).parent().parent().parent();
		var classIndex = tr.attr("class");
		var tdIndex = $(this).parent().parent().attr("class");
		var thirdStatus = $(this).prop("checked");
		
		//选所有按钮
		var btns = $(this).parent().parent().next().find("input");
		$.each(btns,function(i,e){
			e.checked=thirdStatus;
		});
		//选二级菜单
		var secondCks = $("."+classIndex+" ."+tdIndex).find("input");
		$.each(secondCks,function(i,e){
			if(e.checked){
				secondCks[0].checked=true;
			}
		});
		//选主菜单
		var firstCks= $("."+classIndex).find("input");
		$.each(firstCks,function(i,e){
			if(e.checked){
				firstCks[0].checked=true;
			}
		});
		
		//二级菜单下没有被选的，二级菜单也取消选择
		var checked = $("."+classIndex+" ."+tdIndex).find("input:checked");
		if(checked.length == 1){
			$("."+classIndex+" ."+tdIndex).find("input").prop("checked",false);
		}
		//主菜单下没有被选的，主菜单也取消选择
		var checked = $("."+classIndex).find("input:checked");
		if(checked.length == 1){
			firstCks[0].checked=false;
		}
	});
	
	//点击按钮菜单：选上主菜单和子菜单
	$("[name=buttonIds]").click(function(){
		var tr = $(this).parent().parent().parent();
		var classIndex = tr.attr("class");
		var tdIndex = $(this).parent().parent().attr("class")
		var buttonStatus = $(this).prop("checked");
		var thirdTd = $(this).parent().parent().prev().find("input");
		var secondTd = $("."+classIndex+" ."+tdIndex).find("input");
		//选主菜单、二级菜单、三级菜单
		var cks= $("."+classIndex).find("input");
		$.each(cks,function(i,e){
			if(e.checked){
				cks[0].checked=true;
				thirdTd[0].checked=true;
				secondTd[0].checked=true;
			}
		});
		
		/* //三级菜单下没有被选的，三级菜单也取消选择
		var checked = $(this).parent().parent().find("input:checked");
		if(checked.length == 0){
			$(this).parent().parent().prev().find("input").prop("checked",false);
		} */
		
		//二级菜单下没有被选的，二级菜单也取消选择
		var checked = $("."+classIndex+" ."+tdIndex).find("input:checked");
		if(checked.length == 1){
			$("."+classIndex+" ."+tdIndex).find("input").prop("checked",false);
		}
		//主菜单下没有被选的，主菜单也取消选择
		var checked = $("."+classIndex).find("input:checked");
		if(checked.length == 1){
			cks[0].checked=false;
		}
	});
	
	//返回
	$("#back").click(function() {
		window.location.replace("${ctx }/role/roleList.do");
	});
});
</script>
