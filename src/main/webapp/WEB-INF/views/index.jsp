<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="jianda" />
<title>furesky</title>

<link rel=icon type="image/png" href="${pageContext.request.contextPath}/images/000000/logo.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap-treeview.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/highlight/styles/atelier-forest.light.css"/>
</head>
<body>
	<div class="container">
		<div class="row">
			<div id="top_nav">
				<%@ include file="header.jsp" %>
			</div>
			<div id="left_sidebar" class="col-md-2"></div>
			<div id="main_content" class="col-md-10">
				<div id="article_list" class="panel panel-default">
					<div class="panel-heading">
						<a data-toggle="collapse" href="#titleList"> 列表 </a>
					</div>
					<div id="titleList" class="collapse">
						<div class="panel-body">
							<ul>
							</ul>
						</div>
					</div>
				</div>
				<div id="article_info" hidden="hidden">
					<div id="article_id" hidden="hidden"></div>
					<div id="article_title"></div>
					<br />
					<div>
						<ul id="article_label" hidden="hidden">
							<li>Java</li>
							<li>mybatis</li>
						</ul>
					</div>
					<div id="article_content"></div>
				</div>
			</div>
		</div>
		<div class="tool_box">
			<ul>
				<li><a id="edit" class="update"></a></li>
				<li><a href="#"> <img class="img_show" src="images/000000/gotop.png"> <img
						class="img_hide" src="images/000000/gotop1.png">
				</a></li>
			</ul>
		</div>
		<div class="footer">
			<hr>
			<p class="p1">本站提供的内容仅用于培训和测试，不保证内容的正确性。通过使用本站内容随之而来的风险与本站无关。</p>
			<p class="p2">
				<a href="">www.furesky.com</a>
			</p>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/plugins/jquery/jquery-3.4.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/highlight/highlight.pack.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap-treeview.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/initPage.js"></script>
	<script src="${pageContext.request.contextPath}/js/editing.js"></script>
	<script src="${pageContext.request.contextPath}/js/article.js"></script>
	<script type="text/javascript">
		function selectMenu(event, node) {
			if (!isSaved) {
				isSaved = true;
				if (!confirm("你修改的内容未保存，是否真的要离开？")) {
					return;
				}
			}
			$("#article_info").attr("hidden", "hidden");
			onloadArticle(node.catalogId,"loadArticle");
		}
	</script>
</body>
</html>