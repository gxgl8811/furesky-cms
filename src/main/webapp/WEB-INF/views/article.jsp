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
			<div class="col-md-2"></div>
			<div id="main_content" class="col-md-4">
				<form id="addArticleForm">
					<div class="form-group">
						<label for="title">标题</label>
					</div>					
					<div class="form-group">
						<input type="text" class="form-control" name="title" placeholder="title" />
					</div>
					<div class="form-group">
						<label for="catalogName">目录名称</label>
					</div>	
					<div class="form-group">
						<input type="text" class="form-control" name="catalogName" id="catalogName" placeholder="请从左侧选择父目录.." disabled>
					</div>
					<input type="text" name="catalogId" id="catalogId" hidden="hidden">							
					<input type="submit" class="btn btn-default" value="确定">
				</form>
			</div>			
			<div class="col-md-4"></div>
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
	<script type="text/javascript">
		function selectMenu(event, node){
			$("#catalogName").attr("value",node.catalogName);
			$("#catalogId").attr("value",node.catalogId);			
		}
		$("#addArticleForm").submit(function(){	
			$.ajax({
	            async: false,
	            type: "post",
	            url:"addArticle",
	            contentType : "application/x-www-form-urlencoded; charset=utf-8",
	            data:$("#addArticleForm").serialize(),
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