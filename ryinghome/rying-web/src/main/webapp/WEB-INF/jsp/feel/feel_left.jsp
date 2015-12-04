<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="col-xs-2 col-md-2 about">
				<div class="about_le_c" id="about_le_c">
					<p>感受锐英</p>
					<ul>
						<li><a href="<%= basePath%>feel.do" >工作环境</a></li>
						<li><a href="<%= basePath%>act.do">员工活动</a></li>
						
					</ul>
				</div>
				<div class="row">&nbsp;</div>
				<div class="row">&nbsp;</div>
				<img alt="" src="img/tel.png" />
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
