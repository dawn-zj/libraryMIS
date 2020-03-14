$(function(){
	//表格行，鼠标放上去变色
	$(".tr:odd").css("background", "#FFFCEA");
	$(".tr:odd").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#FFFCEA");
		});
	});
	$(".tr:even").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#fff");
		});
	}); 

	/*ie6,7下拉框美化*/
//    if ( $.browser.msie ){
//    	if($.browser.version == '7.0' || $.browser.version == '6.0'){
//    		$('.select').each(function(i){
//			   $(this).parents('.select_border,.select_containers').width($(this).width()+5); 
//			 });
//    		
//    	}
//    }
    
});

//laypage分页
function page(){
	
}

function showTab(tabId, url){
	$('#' + tabId).html('页面加载中，请稍后...');
	$('#' + tabId).load(url); // ajax加载页面
}