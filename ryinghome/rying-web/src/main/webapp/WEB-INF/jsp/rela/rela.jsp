<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<TITLE>上海锐英股份有限公司</TITLE>
<meta name="viewport" content="width=device-width, initial-scale=1">
 <meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet"> -->
	<link rel="shortcut icon" href="img/rying.ico" />
	<link type="text/css" href="css/bootstrap.css" rel="stylesheet" />
	
<script type="text/javascript" src="js/jquery.min.js"></script>
 <script type="text/javascript" src="js/bootstrap.min.js"></script>

<link type="text/css" href="css/lrtk.css" rel="stylesheet" />
<script type="text/javascript" src="js/lrtk.js"></script>
<link type="text/css" href="css/base.css" rel="stylesheet" />


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
		<jsp:include page="../head.jsp" />
		<div class="pic"></div>
		<div class="row">
		<div class="row "> &nbsp;</div>
	 <div class="col-xs-1 col-md-1"></div>
	 				<div class="col-xs-8 col-md-8">
				<p style="font-weight:bold">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投资者关系</p>   
				</div>
				
				
				<a href="<%= basePath%>home.do">返回</a>
			 <div class="row line2"> &nbsp;</div>
				
		</div>		
<!-- 		<hr style="margin-left:140px;margin-top:20px;background-color:#707070;height:2px;width:1000px"></hr>
 -->		
 
		<div class="col-xs-1 col-md-1"></div>
		<div class="row-fluid">
	
			<%-- <jsp:include page="about_left.jsp" /> --%>
			<div class="col-xs-2 col-md-2 about">
				<div class="about_le_c" id="menu">
					<p>投资者关系</p>
					<ul>
						<li><a href="<%= basePath%>rela.do" class="sel" >投资者关系</a></li>
						
						
					</ul>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<img alt="" src="img/tel.png" />
			</div>
			
			<jsp:include page="rela_right.jsp" />
			
			<div class="col-md-2 ">		
			</div>
		</div>
		<jsp:include page="../foot2.jsp" />
	</div>


</BODY>

</HTML>
