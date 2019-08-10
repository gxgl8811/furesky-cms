function onloadArticle(catalogId, type) {
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
				loadArticleList(result.data);
				if (type == "loadArticle") {
					initArticle();
				}
				if (type == "changeCatalog") {
					initArticleList();
				}
			} else {
				alert(result.message);
			}
		},
		error : function(data) {
			alert("404！");
		}
	});
}

//
function initArticleList() {
	// 注册点击事件
	$("#titleList a").click(function() {
		$("#articleId").attr("value",$(this).attr("articleId"));
		$("#articleName").attr("value",$(this).text());
		$("#oldCatalogName").attr("value",$(this).attr("catalogName"));	
		
		$("#del_articleName").attr("value",$(this).text());
		$("#del_articleId").attr("value",$(this).attr("articleId"));
	});
}

//加载文章内容
function initArticle() {
	// 注册点击事件
	$("#titleList a").click(function() {
		if (!isSaved) {
			if (!confirm("你修改的内容未保存，是否真的要离开？")) {
				isSaved = true;
				return;
			}
		}
		$("#article_info").removeAttr("hidden");
		// $("#titleList").attr("class", "collapse");
		getArticle($(this).attr("articleId"));

		// 加载代码高亮
		initHighCode();
	});
}
function loadArticleList(articleList) {
	$("#titleList ul li").remove();
	if (articleList.length == 0) {
		$("#titleList").attr("class", "collapse");
	} else {
		$("#titleList").attr("class", "collapse in");
	}

	var article;
	var title;
	for (var i = 0; i < articleList.length; i++) {
		article=articleList[i];
		title=article.title;
		$("#titleList ul").append(" <li>"
				+"<a catalogId="+article.catalogId+" catalogName="+article.catalogName
				+" articleId=" + article.articleId + ">"+ title.substring(4, title.length - 5) 
				+"</a>" 
				+"</li>");
	}
}
function initHighCode() {
	var codes = $("#article_content").find("code");
	var highCode;
	for (var i = 0; i < codes.length; i++) {
		highCode = hljs.highlightAuto(codes[i].innerText).value;
		codes[i].innerHTML = highCode;
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
