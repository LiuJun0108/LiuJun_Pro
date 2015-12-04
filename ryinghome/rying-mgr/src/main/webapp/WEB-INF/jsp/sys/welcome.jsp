<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>系统欢迎界面</title>
<jsp:include page="../baseInput.jsp"></jsp:include>

</head>

<body>
	<div class="mainindex">
		<div class="welinfo">
			<span><img src="images/sun.png" alt="天气" />
			</span> <b>Admin早上好，欢迎使用信息管理系统</b>(admin@uimaker.com) <a href="#">帐号设置</a>
		</div>
		<div class="welinfo">
			<span><img src="images/time.png" alt="时间" />
			</span> <i>您上次登录的时间：2013-10-09 15:22</i> （不是您登录的？<a href="#">请点这里</a>）
		</div>
		<div class="xline"></div>
		<div style=" width:629px; height:400px;margin-top:10px; background:url(images/mian_w.png) no-repeat center"></div>
	</div>
</body>
</html>
