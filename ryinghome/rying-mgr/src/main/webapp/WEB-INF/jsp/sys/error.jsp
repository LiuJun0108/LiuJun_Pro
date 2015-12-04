<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<jsp:include page="../baseInput.jsp"></jsp:include>
<script type="text/javascript">
$(function() {
	$.messager.confirm('警告信息', '您未登陆或登陆超时，确定退出该系统吗？', function(r){
		if (r){
			top.location.href = 'login.jsp'
		}
	});
})
</script>
</head>
</html>