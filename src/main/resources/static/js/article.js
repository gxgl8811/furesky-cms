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

function loadArticleList(articleList) {
	$("#titleList ul li").remove();
	if (articleList.length == 0) {
		$("#titleList").attr("class", "collapse");
	} else {
		$("#titleList").attr("class", "collapse in");
	}

	var article;
	var title;
	var node;
	for (var i = 0; i < articleList.length; i++) {
		article=articleList[i];
		title=article.title;
		$("#titleList ul").append("<li><a></a></li>");
		node=$("#titleList ul").children("li:eq("+i+")").children("a:first");
		node.attr("catalog_id",article.catalogId);
		node.attr("catalog_name",article.catalogName);
		node.attr("article_id",article.articleId);
		node.text(title.substring(4, title.length - 5));
	}
}

//
function initArticleList() {
	// 注册点击事件
	$("#titleList a").click(function() {
		$("#articleId").attr("value",$(this).attr("article_id"));
		$("#articleName").attr("value",$(this).text());
		$("#oldCatalogName").attr("value",$(this).attr("catalog_name"));	
		
		$("#del_articleName").attr("value",$(this).text());
		$("#del_articleId").attr("value",$(this).attr("article_id"));
	});
}

//加载文章内容
function initArticle() {
	// 注册点击事件
	$("#titleList li a").click(function() {
		if (!isSaved) {
			if (!confirm("你修改的内容未保存，是否真的要离开？")) {
				isSaved = true;
				return;
			}
		}
		$("#article_info").removeAttr("hidden");
		// $("#titleList").attr("class", "collapse");
		getArticle($(this).attr("article_id"));

		// 加载代码高亮
		initHighCode();
	});
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

function initHighCode() {
	var codes = $("#article_content").find("code");
	var highCode;
	for (var i = 0; i < codes.length; i++) {
		highCode = hljs.highlightAuto(codes[i].innerText).value;
		codes[i].innerHTML = highCode;
	}
}


