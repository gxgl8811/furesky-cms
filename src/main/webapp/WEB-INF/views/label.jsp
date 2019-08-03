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
</head>
<body>
	<div class="container">
		<div class="row">
			<div id="top_nav">
				<%@ include file="header.jsp" %>
			</div>
			<br /> <br /> <br />
			<div id="left_sidebar" class="col-md-4"></div>
			<div id="main_content" class="col-md-3">
				<form id="addLabelForm">
					<div class="form-group">
						<label for="labelName">标签名称</label>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="labelName" placeholder="Label Name" />
					</div>
					<input id="submit" type="submit" class="btn btn-default" value="确定">
				</form>
			</div>
			<div id="right_content" class="col-md-5"></div>
		</div>
		<div class="row">
			<br /> <br /> <br /> <br /> <br /> <br />
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
	<script type="text/javascript">
		$("#addLabelForm").submit(function() {
			$.ajax({
				async : false,
				type : "post",
				url : "addLabel",
				contentType : "application/x-www-form-urlencoded",
				data : $("#addLabelForm").serialize(),
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