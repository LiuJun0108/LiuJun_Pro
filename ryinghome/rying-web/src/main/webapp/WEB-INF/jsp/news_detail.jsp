<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<TITLE>上海锐英科技股份有限公司</TITLE>
<meta name="viewport" content="width=device-width, initial-scale=1">
 <meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet"> -->
	<link rel="shortcut icon" href="img/rying.ico" />
	<link type="text/css" href="css/bootstrap.css" rel="stylesheet" />
	


<link type="text/css" href="css/lrtk.css" rel="stylesheet" />

<link type="text/css" href="css/base.css" rel="stylesheet" />

<!-- <script src="http://libs.useso.com/js/jquery/1.11.0/jquery.min.js"></script>
 -->
 <script type="text/javascript" src="js/jquery.min.js"></script>

<!-- <script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script> -->
 <script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/lrtk.js"></script>

<style>
a:hover {
	color: #000;
}

.text-center a {
	color: #707070
}

.text-center a:hover {
	color: #707070
}

.mas a {
	color: #000
}

.mas a:hover {
	color: #707070
}
.about_le_c a:link { 
color:#000; 


}
</style>
<head />



<BODY style="margin: 0px">
<div class="container-fluid" id="LG">
		<jsp:include page="head.jsp" />
	 <div class="row pic">
	 </div> 
	 <div class="row">
	 <div class="row"> &nbsp;</div>
	 
	 <div class="col-xs-1 col-md-1"></div>
	 				<div class="col-xs-8 col-md-8">
				<p style="font-weight:bold">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;锐英新闻</p>   
				</div>
				
				
				<a href="<%= basePath%>about_news.do">返回</a>
					<div class="row line2"> &nbsp;</div>
				
		</div>	
		
	  <div class="row-fluid">
		<div class="col-xs-1 col-md-1"></div>

			<div class="col-xs-2 col-md-2 about">
				<div class="about_le_c" id="menu">
					<p>关于锐英</p>
					<ul>
						<li><a href="<%= basePath%>about.do">锐英简介</a></li>
						<li><a href="<%= basePath%>culture.do">企业文化</a></li>
						<li><a href="<%= basePath%>qua.do">锐英资质</a></li>
						<li><a href="<%= basePath%>stru.do">组织架构</a></li>
						<li><a href="<%= basePath%>proc.do">发展历程</a></li>
						<li><a href="<%= basePath%>about_news.do" class="sel">锐英新闻</a></li>
					</ul>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<img alt="" src="img/tel.png" />
			</div> 

			<jsp:include page="about_news_right_all.jsp" />  
		  </div>
			
			<div class="col-xs-2 col-md-2 ">	</div>
		
		<jsp:include page="foot2.jsp" />
</div> 


</BODY>

</HTML>
