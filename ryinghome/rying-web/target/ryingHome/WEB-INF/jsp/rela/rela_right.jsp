<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
	function u_selectTag(showContent, selfObj) {
		// 操作标签
		var tag = document.getElementById("u_tags").getElementsByTagName("li");
		var taglength = tag.length;
		for (i = 0; i < taglength; i++) {
			tag[i].className = "";
		}
		selfObj.parentNode.className = "u_selectTag";
		// 操作内容
		for (i = 0; j = document.getElementById("u_tagContent" + i); i++) {
			j.style.display = "none";
		}
		document.getElementById(showContent).style.display = "block";
	}
</script>

<div class="col-xs-6 col-md-6 right_content">
	<div class="tag">
		<ul id="u_tags">
			<c:forEach items="${mapList }" var="map" varStatus="st">
				<c:if test="${map.isEmpty == false}">
					<c:choose>
					<c:when test="${st.index == 0}">
						<li class="u_selectTag"><a href="javascript:void(0)" onclick="u_selectTag('u_tagContent${st.index}',this)">${map.year }</a></li>
			        </c:when>
			        <c:otherwise>
			　　　　　	<li><a href="javascript:void(0)" onclick="u_selectTag('u_tagContent${st.index}',this)">${map.year }</a></li>
			　　　	</c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>
		</ul>
		<div id="u_tagContent">
			<c:forEach items="${mapList }" var="map" varStatus="st">
				<c:choose>
				<c:when test="${st.index == 0}">
		　　　　　	<div id="u_tagContent${st.index}" class="u_tagContent u_selectTag" style="display: width: 100%; overflow-y: auto; overflow-x: hidden; display: block;">
						<ul class="rel_u">
							<c:forEach items="${map.list }" var="ir">
								<li>
									<span>
										<fmt:formatDate value="${ir.createtime }" pattern="yyyy-MM-dd"/>
									</span>
									<a href="rela/download.do?show=true&id=${ir.id }">${ir.title }</a>&nbsp&nbsp
									<a href="rela/download.do?show=false&id=${ir.id }"><img src="img/download1.jpg" alt="下载文件" /></a>
								</li>						
							</c:forEach>
						</ul>
					</div>
		        </c:when>
		        <c:otherwise>
	　　　　　	<div id="u_tagContent${st.index}" class="u_tagContent u_selectTag" style="display: none;">
					<ul class="rel_u">
							<c:forEach items="${map.list }" var="ir">
								<li>
									<span>
										<fmt:formatDate value="${ir.createtime }" pattern="yyyy-MM-dd"/>
									</span>
									<a href="rela/download.do?show=true&id=${ir.id }">${ir.title }</a>&nbsp&nbsp
									<a href="rela/download.do?show=false&id=${ir.id }"><img src="img/download1.jpg" alt="下载文件" /></a>
								</li>						
							</c:forEach>
						</ul>
					</div>
		　　　	</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>
</div>