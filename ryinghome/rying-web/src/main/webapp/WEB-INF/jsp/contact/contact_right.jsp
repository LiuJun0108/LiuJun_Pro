<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
    html,body{margin:0;padding:0;}
    .iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
    .iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
<!--  	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GkTKcMPN5amw8KCW7g6DjMVA"></script>
	-->
	<script type="text/javascript" xmlns="http://www.w3.org/1999/xhtml">
</script>
	<style>
	.contact_ri_b p span{font-weight:bold; color:#0886d0; font-size:14px}
	.contact_ri_b p{
	text-indent:0;
	margin-bottom:15px;
    text-indent: 2em;
    line-height: 28px;
    margin: 0 0 30px 15px;
    width: 96%;
    word-wrap: break-word;
    word-break: break-all;
}
	</style>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

		<div class="col-xs-6 col-md-6 right_content">
				<%-- <div class="col-xs-3 col-md-3">
				<p style="font-weight:bold">联系我们</p>   
				</div>
				<div class="col-xs-7 col-md-7">
				</div>
				<div class="col-xs-2 col-md-2">
				<a href="<%= basePath%>home.do">返回</a>
				</div>
		<hr style="margin-left:0px;margin-top:30px;background-color:#707070;height:1px;width:630px"></hr> --%>
				<div class="contact_ri_b">
				<p><span>上海锐英科技股份有限公司</span></br>
				地址：上海市浦东新区浦东软件园郭守敬路498号21栋401室</br>
				电话：021-61016550  </br>
				传真：021-61016551</br>
				邮编：201203</br></p>
				</div>

				<div style="width:740px;height:380px;border:#ccc solid 1px;" id="allmap"></div>


	<script type="text/javascript">
    //创建和初始化地图函数：
       function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
        //addoval();//向地图中添加椭圆图
    }
    
    //创建地图函数：
    function createMap(){
        var map = new BMap.Map("allmap");//在百度地图容器中创建一个地图
        var point = new BMap.Point(121.603877,31.214404);//定义一个中心点坐标
        map.centerAndZoom(point,18);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
        //添加椭圆

        function add_oval(centre,x,y)
    	{
    		var assemble=new Array();
    		var angle;
    		var dot;
    		var tangent=x/y;
    		for(i=0;i<36;i++)
    		{
    			angle = (2* Math.PI / 36) * i;
    			dot = new BMap.Point(centre.lng+Math.sin(angle)*y*tangent, centre.lat+Math.cos(angle)*y);
    			assemble.push(dot);
    		}
    		return assemble;
    	}
    	var oval = new BMap.Polygon(add_oval(point,0.001,0.001), {strokeColor:"#87CEFA", fillColor:"#87CEFA ",strokeWeight:2, fillOpacity:0.3,strokeOpacity:3});
    	map.addOverlay(oval);
    	//添加标注
    	var marker = new BMap.Marker(point);
    	map.addOverlay(marker);              // 将标注添加到地图中
    	var label = new BMap.Label("上海锐英科技股份有限公司",{offset:new BMap.Size(20,-10)});
    	marker.setLabel(label);
    }
    //创建椭圆
    function add_oval(centre,x,y)
	{
		var assemble=new Array();
		var angle;
		var dot;
		var tangent=x/y;
		for(i=0;i<36;i++)
		{
			angle = (2* Math.PI / 36) * i;
			dot = new BMap.Point(centre.lng+Math.sin(angle)*y*tangent, centre.lat+Math.cos(angle)*y);
			assemble.push(dot);
		}
		return assemble;
	}
    
    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }
    
    //地图控件添加函数：
    function addMapControl(){
        //向地图中添加缩放控件
	var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
	map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
	var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
	map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
	map.addControl(ctrl_sca);
    }
   
    initMap();//创建和初始化地图

  </script>
		<div>&nbsp;</div>
   
