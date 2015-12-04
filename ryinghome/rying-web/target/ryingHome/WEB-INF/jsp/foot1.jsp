<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
a:hover {
	color: #b7dff7
}

.text a {
	text-decoration: NONE;
	color: #707070;
	font-size: 16px;
	line-height: 35px
}

.text a p {
	font-weight: bold;
	margin-top: 6px;
	font-family:"微软雅黑";
}

.text ul li a {
	text-decoration: NONE;
	color: #707070;
	line-height: 25px;
	font-size:13px;
	font-family:"微软雅黑";
}

.text ul {
	margin-top: -9px;
}

.text a:hover {
	color: #707070
}


</style>

<div class="row-fluid">&nbsp;</div>

<div class="col-xs-12 col-md-12" style="background: #b7dff7">

	<div class="row-fluid foot-1">

		<div class="col-xs-1 col-md-1"></div>
		
	 <div class="col-xs-2 col-md-2 text">
			
				<a href="<%= basePath%>about.do"><p>关于锐英</p></a>
			       <!--    <hr style="margin-left:0px;margin-top:-8px;background-color:#707070;height:1px;width:80px"></hr> 
-->
			<ul>
				<li><a href="<%= basePath%>about.do">锐英简介</a></li>
				<li><a href="<%= basePath%>culture.do">锐英文化</a></li>
				<li><a href="<%= basePath%>qua.do">锐英资质</a></li>
				<li><a href="<%= basePath%>stru.do"> 锐英结构</a></li>
			</ul>
		</div>
		
	
		
		
		<div class="col-xs-2 col-md-2 text">
			
				<a href="<%= basePath%>feel.do"><p>感受锐英</p></a>
			<!--           <hr style="margin-left:0px;margin-top:-8px;background-color:#707070;height:1px;width:80px"></hr>
 -->
			<ul>
				<li><a href="<%= basePath%>feel.do">工作环境</a></li>
				<li><a href="<%= basePath%>act.do">员工活动</a></li>
				<li><a href="<%= basePath%>act.do">活动掠影</a></li>
			</ul>
		</div>

		<div class="col-xs-2 col-md-2 text">
			
				<a href="<%= basePath%>join.do"><p>加入锐英</p></a>
			<!--<hr style="background-color:#ADD8E6;height:2px"></hr> -->
			<!-- 			<hr style="margin-left:0px;margin-top:-8px;background-color:#707070;height:1px;width:80px"></hr> 
 -->
			<ul>
				<li><a href="<%= basePath%>join.do">选择锐英理由</a></li>
				<li><a href="<%= basePath%>letter.do">给求职者的一封信</a></li>
				<li><a href="<%= basePath%>post.do">招聘职位</a></li>
				<li><a href="<%= basePath%>process.do">招聘流程</a></li>
			</ul>
		</div>
		<div class="col-xs-5 col-md-5 text">


		
			<a  href="<%= basePath%>contact.do"><p>联系锐英</p></a>
			
       		<ul>
       		<li><a href="<%= basePath%>contact.do">上海&nbsp;&nbsp;</a>
       		<a href="<%= basePath%>contact.do">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;武汉&nbsp;&nbsp;</a>
       		<a href="<%= basePath%>contact.do">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新疆&nbsp;&nbsp;</a>
       		</li>
      		<li><img alt="" src="img/tb1.png"/>&nbsp;&nbsp;<a>电话: 021-61016550</a></li>
      		<li><img alt="" src="img/tb2.png"/>&nbsp;&nbsp;<a>传真: 021-61016551</a></li>
      		<li><img alt="" src="img/tb3.png"/>&nbsp;&nbsp;<a>地址: 上海市浦东新区郭守敬路498号21号楼401室</a></li>
            </ul>
        </div>
	<div class="row">
	&nbsp;
	</div>
