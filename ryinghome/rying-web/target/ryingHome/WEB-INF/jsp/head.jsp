<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="row-fluid head-1"
	style="height: 90px; background: url('img/head_bg.png');">
	<div class="col-xs-1 col-md-1"></div>
	<img style="float: left;margin-top:4px;margin-left: 100px;" alt="" src="img/logo.png" width="70px" height="80px" />
	<!-- <div class="col-xs-1 col-md-1 logo " style="margin-left:88px;line-height:auto">
		<img alt="" src="img/logo.png" width="70px" height="80px" />
		</div> -->
		
	<div class="col-xs-6 col-md-6 text" style="margin-top: 7px;" >
		
		<span style="font-family: SimHei ;font-size:28px ;text-align:center; letter-spacing:1.6px;  ">上海锐英科技股份有限公司<br></span>
		<span style="font-family: Arial ;font-size:21px ;text-align:center;  ">Shanghai&nbspRying&nbspTechnology&nbspCO.,LTD</span>
		<span style="font-family: SimHei ;font-size:16px ;text-align:center;  " >&nbsp&nbsp&nbsp证券代码：834378</span>
		
	</div>
	<div class="col-xs-2 col-md-2 " style="margin-top: 32px;">
	
	
		<a href="javascript:window.external.AddFavorite('http://www.rying.com.cn', '锐英科技')"><font face="SimHei"size="4"> 收藏 </font></a>&nbsp;&nbsp;
		<a href="#" class="hrefs" onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('');"><font face="SimHei"size="4">设为首页</font></a><br>
	
	</div>
	</div>

<div class="menu_b">
 <ul class="menu">
  <li><a href="<%= basePath%>home.do"><span>首 页</span></a></li>
 
  <li><a href="<%= basePath%>about.do"><span>关于锐英</span></a>
     <ul>
      <li><a href="<%= basePath%>about.do">锐英简介</a></li>
      <li><a href="<%= basePath%>culture.do">企业文化</a></li>
      <li><a href="<%= basePath%>qua.do">锐英资质</a></li>
      <li><a href="<%= basePath%>stru.do">组织架构</a></li>
      <li><a href="<%= basePath%>proc.do">发展历程</a></li>
      <li><a href="<%= basePath%>about_news.do">锐英新闻</a></li>
    </ul>
  </li>
  <li><a href="<%= basePath%>feel.do"><span>感受锐英</span></a>
     <ul>
      <li><a href="<%= basePath%>feel.do">工作环境</a></li>
      <li><a href="<%= basePath%>act.do">员工活动</a></li>
     </ul>
  </li>
  <li><a href="<%= basePath%>join.do"><span>加入锐英</span></a>
     <ul>
      <li><a href="<%= basePath%>join.do">选择锐英的理由</a></li>
      <li><a href="<%= basePath%>letter.do">给求职者的信</a></li>
      <li><a href="<%= basePath%>post.do">招聘职位</a></li>
      <li><a href="<%= basePath%>process.do">招聘流程</a></li>
     </ul>
  </li>
  <li><a href="<%= basePath%>culture.do"><span>锐英文化</span></a></li>
  <li><a href="<%= basePath%>rela.do"><span>投资者关系</span></a></li>
  <li><a href="<%= basePath%>contact.do"><span>联系我们</span></a></li>
  
 </ul>
</div>

