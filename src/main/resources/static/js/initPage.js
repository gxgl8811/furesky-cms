
$(function(){
	initPage();
});

/**
 * 加载页面内容
 */
function initPage(){
	//注册点击事件
	$(".dropdown-menu a[href='#']").click(function(){
		var className=$(this).text();
		$("#top_nav").attr("data_current_class",className);
		onloadMenu();
	});
	
	//初次加载
	onloadMenu();
}

/**
 * 加载菜单
 */
function onloadMenu(className){
	var className=$("#top_nav").attr("data_current_class");	
	$.ajax({
		url : "/furesky/cms/menus",
		type : "post",		
		contentType : "application/x-www-form-urlencoded",
		data : {
			"className" : className
		},
		dataType : "json",
		success : function(result) {
			if(result.successful){
				json_data=JSON.stringify(result.data);
				initMenu(json_data);
			}else{
				alert(result.message);
				initMenu([]);
			}
		},
		error : function(err) {
			alert("404！");
		}
	});
}

/**
 * 初始化菜单树
 */
function initMenu(treeData){
	$('#left_sidebar').treeview({
		data : treeData,

		color: "#000000",
		backColor: "#f8f8f8",
		onhoverColor:"#dcdcdc",
		selectedBackColor:"#B4B4C3",
		levels:3,
		collapseIcon:"glyphicon glyphicon-chevron-up",
		expandIcon:"glyphicon glyphicon-chevron-down",
		onNodeSelected: selectMenu
	});
}


