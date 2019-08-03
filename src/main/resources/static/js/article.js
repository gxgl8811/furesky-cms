function onloadArticle(catalogId) {
	$.ajax({
		async : false,
		type : "post",
		url : "/furesky/cms/article/getList",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"catalogId" : catalogId
		},
		dataType : "json",
		success : function(result) {
			if (result.successful) {
				initArticlePage(result.data);
			} else {
				alert(result.message);
			}
		},
		error : function(data) {
			alert("404！");
		}
	});
}

function initArticlePage(articleList) {
	$("#titleList ul li").remove();
	if (articleList.length == 0) {
		$("#titleList").attr("class", "collapse");
	} else {
		$("#titleList").attr("class", "collapse in");
	}

	var title;
	for (var i = 0; i < articleList.length; i++) {
		title = articleList[i].title;
		$("#titleList ul").append(
				" <li><a articleId=" + articleList[i].articleId + ">"
						+ title.substring(4, title.length - 5) + "</a></li>");
	}

	// 注册点击事件
	$("#titleList a").click(function() {
		if (!isSaved) {
			if (!confirm("你修改的内容未保存，是否真的要离开？")) {
				isSaved = true;
				return;
			}
		}
		$("#article_info").removeAttr("hidden");
		//$("#titleList").attr("class", "collapse");
		getArticle($(this).attr("articleId"));
		
		//加载代码高亮
		initHighCode();
	});
}
function initHighCode(){
	var codes=$("#article_content").find("code");
	var highCode;
	for(var i = 0; i < codes.length; i++) {
		highCode=hljs.highlightAuto(codes[i].innerText).value;
		codes[i].innerHTML=highCode;
	}	
}

function getArticle(articleId) {
	$.ajax({
		async : false,
		type : "post",
		url : "/furesky/cms/article/get",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"articleId" : articleId
		},
		dataType : "json",
		success : function(result) {
			if (result.successful) {
				$("#article_id").text(result.data.articleId);
				$("#article_title").html(result.data.title);
				$("#article_content").html(result.data.content);
			} else {
				alert(result.message);
			}
		},
		error : function(data) {
			alert("404！");
		}
	});
}

