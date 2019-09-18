<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}" title="furesky"> <img class="img_show"
			src="${pageContext.request.contextPath}/images/000000/furesky.png" /> <img class="img_hide"
			src="${pageContext.request.contextPath}/images/000000/furesky1.png" />
		</a>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
					role="button" aria-haspopup="true" aria-expanded="false">View<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">HTML</a></li>
						<li><a href="#">CSS</a></li>						
						<li role="separator" class="divider"></li>
						<li><a href="#">...</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">...</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
					role="button" aria-haspopup="true" aria-expanded="false">Server <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Linux</a></li>						
						<li role="separator" class="divider"></li>	
						<li><a href="#">JVM</a></li>					
						<li><a href="#">JavaSE</a></li>
						<li><a href="#">JavaEE</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">RegEx</a></li>
						<li><a href="#">Design Pattern</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Context</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
					role="button" aria-haspopup="true" aria-expanded="false">Data <span class="caret"></span></a>
					<ul class="dropdown-menu">						
						<li><a href="#">MySQL</a></li>
						<li><a href="#">NoSQL</a></li>		
						<li role="separator" class="divider"></li>						
						<li><a href="#">...</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
					role="button" aria-haspopup="true" aria-expanded="false">Frame <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">SpringMVC</a></li>
						<li><a href="#">Spring</a></li>						
						<li><a href="#">Spring Boot</a></li>
						<li><a href="#">Spring Cloud</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">MyBatis</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">ZooKeeper</a></li>
						<li><a href="#">Kafka</a></li>
						<li><a href="#">Dubbo</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
					role="button" aria-haspopup="true" aria-expanded="false">DveTool <span class="caret"></span></a>
					<ul class="dropdown-menu">						
						<li><a href="#">Eclipse</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Git/GitHub</a></li>
						<li><a href="#">Kettle</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Hadoop</a></li>
						<li><a href="#">Hive</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">...</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
					role="button" aria-haspopup="true" aria-expanded="false">Other <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">MyFiles</a></li>
						<li><a href="#">Employment</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">...</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">...</a></li>						
						<li><a href="#">...</a></li>
					</ul></li>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
					role="button" aria-haspopup="true" aria-expanded="false"><span
						class="glyphicon glyphicon-user"></span></a>
					<ul class="dropdown-menu">
						<!-- <li><a href="cms/label/add">新建标签</a></li> -->
						<li><a href="${pageContext.request.contextPath}/cms/catalog/edit">编辑目录</a></li>
						<li><a href="${pageContext.request.contextPath}/cms/article/add">新建文章</a></li>
						<li><a href="${pageContext.request.contextPath}/cms/article/update">修改文章</a></li>
					</ul></li>
			</ul>
			<form class="navbar-form navbar-right" action="http://www.baidu.com/baidu" target="_blank">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search" name=word> <span
						class="input-group-btn">
						<button class="btn glyphicon glyphicon-search"></button>
					</span>
				</div>
			</form>
		</div>
	</div>
</nav>