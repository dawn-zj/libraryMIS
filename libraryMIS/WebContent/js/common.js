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
function page(cont,pages,pageNum,url,judge){
	//分页
	laypage({
		cont : cont,
		skip : true,//跳转页面选项
		skin: '#0066CC',
		pages : pages, //可以叫服务端把总页数放在某一个隐藏域，再获取。假设我们获取到的是18

		curr : function() { //通过url获取当前页，也可以同上（pages）方式获取
			var pageNo = pageNum; // 当前页(后台获取到的)
			return pageNo ? pageNo : 1; // 返回当前页码值
		}(),
		jump : function(e, first) { //触发分页后的回调
			if(!judge){//---judge--是用来判断某个div加载
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					window.location.replace(url+e.curr);
				}
			}else{
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					$(judge).load(url+ e.curr);
				}
			}
		}
	});
}

function showTab(tabId, url){
	$('#' + tabId).html('页面加载中，请稍后...');
	$('#' + tabId).load(url); // ajax加载页面
}