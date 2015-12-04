<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>

  
</style>
<div class="col-xs-2 col-md-2 about">
				 <div class="about_le_c" id="about_le_c">
					<p>关于锐英</p>
					<ul>
						<li><a href="<%= basePath%>about.do">锐英简介</a></li>
						<li><a href="<%= basePath%>culture.do">企业文化</a></li>
						<li><a href="<%= basePath%>qua.do">锐英资质</a></li>
						<li><a href="<%= basePath%>stru.do">组织架构</a></li>
						<li><a href="<%= basePath%>proc.do">发展历程</a></li>
						<li><a href="<%= basePath%>about_news.do">锐英新闻</a></li>
					</ul>
				</div> 
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<img alt="" src="img/tel.png"  />
			</div>
<script type="text/javascript">
  var urlstr = location.href;
  //alert((urlstr + '/').indexOf($(this).attr('href')));
  //alert($("#about_le_c a").attr('href'));
  var urlstatus=false;
 
  $("#about_le_c a").each(function () {
//alert((urlstr + '/').indexOf($(this).attr('href')));
//alert(urlstatus);
    if ((urlstr + '/').indexOf($(this).attr('href')) > -1&&$(this).attr('href')!='') {
      $(this).addClass('sel'); urlstatus = true;
    } else {
      $(this).removeClass('sel');
    } 
  });
  if (!urlstatus) {$("#about_le_c a").eq(0).addClass('sel'); }

</script>