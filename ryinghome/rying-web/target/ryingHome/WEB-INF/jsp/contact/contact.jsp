<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN" >
<head>
<TITLE>上海锐英科技股份有限公司</TITLE>
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
 a.cur{color:#0886d0;
 border-left:5px #0886d0 solid;
 background:url() no-repeat right -718px;
 text-decoration:none; 
}

</style>


 


<head />



<BODY style="margin: 0px">
	<div class="container-fluid" id="LG">
		<jsp:include page="../head.jsp" />
		<div class="row">
		<img alt="" src="img/contact.jpg" width="100%" height="200px">
		</div>
		<div class="row">
		<div class="row"> &nbsp;</div>
	 <div class="col-xs-1 col-md-1"></div>
	 				<div class="col-xs-8 col-md-8">
				<p style="font-weight:bold">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系我们</p>   
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
					<p>联系我们</p>
					<ul>
						<li><a href="<%= basePath%>contact.do" class="sel" >上海总公司</a></li>
					</ul>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<img alt="" src="img/tel.png" />
			</div>
			
			<jsp:include page="contact_right.jsp" />
			
			<div class="col-xs-2 col-md-2 ">	
</div>
				
			</div>
		</div>
		<jsp:include page="../foot2.jsp" />
	</div>


</BODY>

</HTML>
