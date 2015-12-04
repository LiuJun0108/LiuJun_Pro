<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>后台管理平台</title>
<jsp:include page="baseInput.jsp"></jsp:include>
</head>

<body>
	<div id="cc" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north', href:'layout/top.html'" style="height:90px;"></div>
		<div data-options="title:'导航',region:'west',iconCls:'icon-reload',href:'layout/west.html'" style="width:150px;"></div>
		<div data-options="region:'center',href:'layout/center.html'"></div>
	</div>
</body>
</html>
