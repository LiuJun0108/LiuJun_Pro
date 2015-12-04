<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="col-xs-6 col-md-6 right_content">
	<div>
		<c:forEach items="${newsList }" var="news">
			<div class="row 1">&nbsp</div>
			<a href="<%=basePath%>news_detail?id=${news.id}"> ${news.title }
			</a><span1>
			<fmt:formatDate value="${news.createtime }" pattern="yyyy-MM-dd"/></span>
			</span1>
		</c:forEach>
	</div>
	<nav class="nav2">
		<ul class="pagination" style="margin-top: 200px; margin-left: 200px;">
			<li><a href="javascript:previous();" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
			</a></li>

			<c:if test="${pageNo - 2 > 0 }">
				<li><a href="javascript:pageination(${pageNo - 2 })">${pageNo - 2 }</a></li>
			</c:if>
			<c:if test="${pageNo - 1 > 0 }">
				<li><a href="javascript:pageination(${pageNo - 1 })">${pageNo - 1 }</a></li>
			</c:if>

			<li class="active"><a href="#">${pageNo }</a></li>

			<c:if test="${pageNo + 1 <= pageCount }">
				<li><a href="javascript:pageination(${pageNo + 1 })">${pageNo + 1 }</a></li>
			</c:if>
			<c:if test="${pageNo + 2 <= pageCount }">
				<li><a href="javascript:pageination(${pageNo + 2 })">${pageNo + 2 }</a></li>
			</c:if>

			<li><a href="javascript:next();" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span></a></li>
		</ul>
	</nav>
</div>
<script type="text/javascript">
	function pageination(pageNo) {
		location.href = 'about_news?pageNo=' + pageNo;
	}

	function next() {
		var count = $('.active').nextAll('li').length;
		if (count > 1) {
			var pageNo = $('.active a').text();
			location.href = 'about_news?pageNo=' + (parseInt(pageNo) + 1);
		}
	}

	function previous() {
		var count = $('.active').prevAll('li').length;
		if (count > 1) {
			var pageNo = $('.active a').text();
			location.href = 'about_news?pageNo=' + (parseInt(pageNo) - 1);
		}
	}
</script>
