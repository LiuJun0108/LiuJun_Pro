<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="../baseInput.jsp"></jsp:include>
<style type="text/css">
li {width:240px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;} 
</style>
<script type="text/javascript">
var datagrid;
var dd;

var url;

$(function() {
	datagrid = $('#dg').datagrid({
		url : 'news/datagrid.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		striped : false,
		singleSelect : true,
		idField : 'id',
		columns : [ [ {
			title : '新闻标题',
			field : 'title',
			sortable : true
		}, {
			title : '新闻内容',
			field : 'content',
			sortable : true,
			formatter: function(value,row,index){
				if (value != null && value.length > 100){
					value =  value.replace(/<[^>]+>/g,"");//去掉所有的html标记
					value = value.substring(0, 100)+'...';
				}
				return value;
			}
		}, {
			field : 'createtime',
			title : '创建时间',
			align : 'center',
			sortable : true
		}, {
			field : 'updatetime',
			title : '最后一次修改时间',
			align : 'center',
			sortable : true
		}, {
			field : 'id',
			title : '操作',
			align : 'center',
			formatter: function(value,row,index){
				return '<a href="#" class="formaLink" onclick="showNews('+value+','+ index +');"></a>';
			}
		} ] ],
		toolbar : [ {
			plain : true,
			iconCls : 'icon-add',
			text : '添加',
			handler : function() {
				url = "news/addNews.html";
				location.href = url;
			}
		},'-',{
			plain : true,
			iconCls : 'icon-edit',
			text : '编辑',
			handler : function() {
				var row = datagrid.datagrid('getSelected');
				if (row) {
					url = 'news/updateNews.html?id=' + row.id;
					location.href = url;
				}
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var row = datagrid.datagrid('getSelected');
				if(row) {
					$.messager.confirm('系统提示', '您想要删除该条数据吗？', function(r){
						if (r){
							$.post('news/destroyNews.action',{id:row.id},function(result){
	                            if (result.success){
	                                datagrid.datagrid('reload');    // reload the user data
	                                $('#dd').dialog('close');
	                                $.messager.alert('系统提示',result.message,'info');
	                            } else {
	                                $.messager.alert('系统提示',result.message,'error');
	                            }
	                        },'json');
						}
					});
				}else {
					$.messager.show({
	                	title:'系统提示',
		                msg:'请选择一条记录',
		                timeout:2000,
		                showType:'fade',
		                style:{
		                    right:'',
		                    bottom:''
		                }
		            });
				}
			}
		}, '-', {
			text : '帮助',
			iconCls : 'icon-help',
			handler : function() {
				$.messager.alert('系统提示','系统提示','info');
			}
		} ],
		onLoadSuccess : function(data) {
			$('.formaLink').linkbutton({
				plain : true,
			    iconCls: 'icon-search',
			    text : '查看'
			}); 
		}

	});

	dd = $('#dd').dialog({
		width : 400,
		height : 280,
		modal : true,
		closed : true,
		title : '添加用户',
		toolbar : [ {
			text : '帮助',
			iconCls : 'icon-help',
			handler : function() {
				alert('help');
			}
		} ],
		buttons : [ {
			iconCls : 'icon-ok',
			text : '保存',
			handler : function() {
				$('#addForm').form('submit', {
					url : url,
					onSubmit : function() {
						return $(this).form('validate');
					},
					success : function(data) {
						var data = eval('(' + data + ')'); // change the JSON string to javascript object    
						if (data.success) {
							datagrid.datagrid('reload');
							
							var message = data.message;
							
							var obj = data.obj;
							if(obj && obj.flag == 'update'){
								$.messager.alert('系统提示',message,'info');
								$('#dd').dialog('close');
							} else {
								$.messager.confirm('系统提示', message + '！是否继续添加？', function(r){
									if (r){
									    $('#addForm').form('clear');
									}else {
										$('#dd').dialog('close');
									}
								});
							}
						}
					}
				});
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#dd').dialog('close');
			}
		} ]
	});
	
	
});

function showNews(newsId, index) {
	window.open('news/showNews.action?id='+newsId);
};
</script>

</head>
<body>
	<div id="cc" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',title:'按条件查询',split:true,collapsed:true" href="news/selectPage.html" style="height:80px;"></div>
		<div data-options="region:'center'">
			<table id="dg"></table>
		</div>
	</div>
</body>

</html>
