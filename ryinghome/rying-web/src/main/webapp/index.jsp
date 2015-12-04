<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
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
<head/>
<body>

	<jsp:forward page="home.do"/>
	
</body>
</html>
