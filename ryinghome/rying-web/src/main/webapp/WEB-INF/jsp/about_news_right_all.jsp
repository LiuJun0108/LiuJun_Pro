<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- <style>
.about_tx  {
	text-indent: 2em;
}

</style> -->
<div class="col-xs-6 col-md-6 right_content">

	<div>
		<div class="about_tx">
			<h3>${news.title }</h3>
			<p>
				<br>
			</p>
			<span itemprop="datePublished">
				<fmt:formatDate value="${news.createtime }" pattern="yyyy-MM-dd"/></span>
			<p style="text-indent: 2em">
				<br> ${news.content }
			</p>

		</div>


	</div>

</div>


