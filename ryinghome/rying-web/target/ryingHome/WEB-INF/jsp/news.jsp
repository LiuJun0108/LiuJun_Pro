<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="row-fluid news">
	<div class="row">&nbsp</div>
	<div class="row">&nbsp</div>

	<div class="col-xs-1 col-md-1"></div>
	<div class="col-xs-4 col-md-4">
		<div id="right">
			<h4 style="font-family: SongTi;color: #0886d0;"><b>公司简介</b></h4>
			
		</div>
		
		<div class="row">&nbsp</div>
		<div class="intr">
		上海锐英科技股份有限公司（证券名称：锐英科技，证券代码：834378），位于国家级软件园-上海浦东软件园内，是上海市政府认定的“软件企业”“高新技术企业”，致力于信息安全行业中的电子数据取证、网络信息安全等软硬件产品的产品开发、技术研究、安全集成与整体服务，为相关部门提供互联网信息安全产品、技术培训与技术服务的专业化企业。
		</div>
	</div>
	<div class="col-xs-1 col-md-1"></div>
	<div id="div1">
		<li></li>
	</div>
	<div class="col-xs-4 col-md-4">
	
	<div class="row">
	
			<h4 style="font-family: SongTi;color: #0886d0; margin-left: 11px;"><b>新闻动态</b></h4>
			
	
	<a href="<%=basePath%>about_news.do" style="font-family: SongTi; color: #808080;float: right;">全部>>&nbsp<br></a>
	&nbsp</div>
	<li>

		<div class="row content">&nbsp</div>
		</li>
		<li>
			<div class="row content">&nbsp</div>
		</li>
		<li>
			<div class="row content">&nbsp</div>
		</li>
		<li>
			<div class="row content">&nbsp</div>
		</li>
		<li>
			<div class="row content">&nbsp</div>
		</li>
	</div>
</div>

<div class="row">&nbsp</div>
<div class="row">&nbsp</div>


<script type="text/javascript">

$(function() {
	$.get('news/nearest.do',{count : 10},function(d) {
		if(d) {
			var row = $('.content');
			$(row[0]).after('<span>'+d[0].createtime.substring(0,10)+'</span><img alt="" src="img/jt2.jpg">&nbsp<a href="news_detail.do?id='+d[0].id+'">'+d[0].title+'</a>');
			$(row[1]).after('<span>'+d[1].createtime.substring(0,10)+'</span><img alt="" src="img/jt2.jpg">&nbsp<a href="news_detail.do?id='+d[1].id+'">'+d[1].title+'</a>');
			$(row[2]).after('<span>'+d[2].createtime.substring(0,10)+'</span><img alt="" src="img/jt2.jpg">&nbsp<a href="news_detail.do?id='+d[2].id+'">'+d[2].title+'</a>');
			$(row[3]).after('<span>'+d[3].createtime.substring(0,10)+'</span><img alt="" src="img/jt2.jpg">&nbsp<a href="news_detail.do?id='+d[3].id+'">'+d[3].title+'</a>');
			$(row[4]).after('<span>'+d[4].createtime.substring(0,10)+'</span><img alt="" src="img/jt2.jpg">&nbsp<a href="news_detail.do?id='+d[4].id+'">'+d[4].title+'</a>');
	
			$(row[5]).after('<span>'+d[5].createtime.substring(0,10)+'</span><img alt="" src="img/jt2.jpg">&nbsp<a href="news_detail.do?id='+d[5].id+'">'+d[5].title+'</a>');
			$(row[6]).after('<span>'+d[6].createtime.substring(0,10)+'</span><img alt="" src="img/jt2.jpg">&nbsp<a href="news_detail.do?id='+d[6].id+'">'+d[6].title+'</a>');
			$(row[7]).after('<span>'+d[7].createtime.substring(0,10)+'</span><img alt="" src="img/jt2.jpg">&nbsp<a href="news_detail.do?id='+d[7].id+'">'+d[7].title+'</a>');
			$(row[8]).after('<span>'+d[8].createtime.substring(0,10)+'</span><img alt="" src="img/jt2.jpg">&nbsp<a href="news_detail.do?id='+d[8].id+'">'+d[8].title+'</a>');
			$(row[9]).after('<span>'+d[9].createtime.substring(0,10)+'</span><img alt="" src="img/jt2.jpg">&nbsp<a href="news_detail.do?id='+d[9].id+'">'+d[9].title+'</a>');
		}
	},'json');
});
</script>
