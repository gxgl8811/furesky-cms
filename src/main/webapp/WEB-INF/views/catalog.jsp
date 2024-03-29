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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap-treeview.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div id="top_nav">
				<%@ include file="header.jsp"%>
			</div>
			<div id="left_sidebar" class="col-md-2"></div>
			<div class="col-md-1"></div>
			<div id="main_content" class="col-md-3">
				<h3><span class="label label-primary">添加新目录</span></h3>
				<br/>
				<form id="addCatalogForm">
					<div class="form-group">
						<label for="catalogName">名称：</label>
						<input type="text" class="form-control" name="catalogName" placeholder="" />
					</div>
					<div class="form-group">
						<label for="parentName">父目录：</label>
						<input type="text" class="form-control" name="parentName" id="parentName"
							placeholder="请从左侧选择父目录..." disabled>
					</div>
					<div class="form-group">
						<label for="rank">在父目录下的排序：</label>
						<input type="text" class="form-control" name="rank" id="rank" value="1">
					</div>
					<input type="text" name="parentId" id="parentId" hidden="hidden" /> <input type="submit"
						class="btn btn-default" value="确定" />
				</form>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-3">
				<h3><span class="label label-primary">修改目录名称</span></h3>
				<br/>
				<form id="updateCatalogForm">
					<div class="form-group">
						<label for="newCatalogName">新名称：</label>
						<input type="text" class="form-control" name="newCatalogName" placeholder="" />
					</div>
					<div class="form-group">
						<label for="oldCatalogName">原目录：</label>
						<input type="text" class="form-control" name="oldCatalogName" id="oldCatalogName"
							placeholder="请从左侧选择原目录..." disabled>
					</div>
					<input type="text" name="oldCatalogId" id="oldCatalogId" hidden="hidden" /> <input
						type="submit" class="btn btn-default" value="确定" />
				</form>
			</div>
			<div class="col-md-2"></div>
		</div>
		<div class="row">
			<br /> <br /> <br />
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
		function selectMenu(event, node) {
			$("#parentName").attr("value", node.catalogName);
			$("#parentId").attr("value", node.catalogId);
			$("#oldCatalogName").attr("value", node.catalogName);
			$("#oldCatalogId").attr("value", node.catalogId);
		}
		$("#addCatalogForm").submit(function() {
			$.ajax({
				async : false,
				type : "post",
				url : "addCatalog",
				contentType : "application/x-www-form-urlencoded",
				data : $("#addCatalogForm").serialize(),
				dataType : "json",
				success : function(result) {
					alert(result.message);
				},
				error : function(err) {
					alert("404！");
				}
			});
		});
		$("#updateCatalogForm").submit(function() {
			$.ajax({
				async : false,
				type : "post",
				url : "updateCatalog",
				contentType : "application/x-www-form-urlencoded",
				data : $("#updateCatalogForm").serialize(),
				dataType : "json",
				success : function(result) {
					alert(result.message);
				},
				error : function(err) {
					alert("404！");
				}
			});
		});
	</script>
</body>
</html>