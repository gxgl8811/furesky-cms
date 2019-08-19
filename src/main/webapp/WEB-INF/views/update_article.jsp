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
</head>
<body>
	<div class="container">		
		<div class="row">
			<div id="top_nav">
				<%@ include file="header.jsp" %>
			</div>
			<div id="left_sidebar" class="col-md-2"></div>
			<div id="main_content" class="col-md-4">
				<div id="article_list" class="panel panel-default">
					<div class="panel-heading">
						<a data-toggle="collapse" href="#titleList"> 文章列表 </a>
					</div>
					<div id="titleList" class="collapse">
						<div class="panel-body">
							<ul>
							</ul>
						</div>
					</div>
				</div>
			</div>			
			<div id="main_content" class="col-md-5">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a data-toggle="collapse" href="#del_article"> 删除文章 </a>
					</div>
					<div id="del_article" class="collapse">
						<div class="panel-body">
							<form id="delAticleForm">
								<div class="form-group">
									<label for="del_articleName">文章：</label>
									<input type="text" class="form-control" name="del_articleName" id="del_articleName" placeholder="请从文章列表中选择..." disabled>
								</div>
								<input type="text" name="del_articleId" id="del_articleId" hidden="hidden">							
								<input type="submit" class="btn btn-default" value="删除"  onclick="return confirm('确定要删除?')">
							</form>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a data-toggle="collapse" href="#change_catalog"> 更改文章目录 </a>
					</div>
					<div id="change_catalog" class="collapse">
						<div class="panel-body">
							<form id="changeCatalogForm">
								<div class="form-group">
									<label for="articleName">文章：</label>
									<input type="text" class="form-control" name="articleName" id="articleName" placeholder="请从文章列表中选择..." disabled>
								</div>
								<div class="form-group">
									<label for="oldCatalogName">原目录：</label>
									<input type="text" class="form-control" name="oldCatalogName" id="oldCatalogName" disabled>
								</div>								
								<div class="form-group">
									<label for="newCatalogName">新目录：</label>
									<input type="text" class="form-control" name="newCatalogName" id="newCatalogName" placeholder="请从目录树种选择..." disabled>
								</div>
								<input type="text" name="newCatalogId" id="newCatalogId" hidden="hidden">
								<input type="text" name="articleId" id="articleId" hidden="hidden">							
								<input type="submit" class="btn btn-default" value="确定">
							</form>
						</div>
					</div>
				</div>
			</div>			
			<div class="col-md-1"></div>					
		</div>
		<div class="row">
			<br/><br/><br/>
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
	<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap-treeview.min.js"></script>	
	<script src="${pageContext.request.contextPath}/js/initPage.js"></script>
	<script src="${pageContext.request.contextPath}/js/article.js"></script>
	<script type="text/javascript">
		function selectMenu(event, node){
			$("#newCatalogName").attr("value",node.catalogName);
			$("#newCatalogId").attr("value",node.catalogId);
			onloadArticle(node.catalogId, "changeCatalog");
		}
		$("#changeCatalogForm").submit(function(){	
			$.ajax({
	            async: false,
	            type: "post",
	            url:"changeCatalog",
	            contentType : "application/x-www-form-urlencoded; charset=utf-8",
	            data:$("#changeCatalogForm").serialize(),
	            dataType: "json",
	            success: function (result) {
	            	alert(result.message);
	            },
	            error: function (err) {
	            	alert("404！");
	            }
	        });
	    });		
		$("#delAticleForm").submit(function(){	
			$.ajax({
	            async: false,
	            type: "post",
	            url:"delete",
	            contentType : "application/x-www-form-urlencoded; charset=utf-8",
	            data:$("#delAticleForm").serialize(),
	            dataType: "json",
	            success: function (result) {
	            	alert(result.message);
	            },
	            error: function (err) {
	            	alert("404！");
	            }
	        });
	    });	
	</script>
</body>
</html>