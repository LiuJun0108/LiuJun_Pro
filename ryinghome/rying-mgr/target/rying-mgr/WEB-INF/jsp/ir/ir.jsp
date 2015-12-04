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
<script type="text/javascript">
var datagrid;
var dd;

var url;

$(function() {
	datagrid = $('#dg').datagrid({
		url : 'ir/datagrid.action',
		pagination : true,
		fit : true,
		rownumbers : true,
		striped : false,
		singleSelect : true,
		idField : 'id',
		columns : [ [ {
			title : '标题',
			field : 'title',
			sortable : true
		}, {
			title : '文件路径',
			field : 'filePath',
			sortable : true
		}, {
			field : 'publishUser',
			title : '发布人',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				return value.username;
			}
		}, {
			field : 'createtime',
			title : '发布时间',
			align : 'center',
			sortable : true
		}, {
			field : 'id',
			title : '操作',
			align : 'center',
			formatter: function(value,row,index){
				return '<a href="#" class="formaLink" onclick="downloadIr('+value+','+ index +');"></a>';
			}
		} ] ],
		toolbar : [ {
			plain : true,
			iconCls : 'icon-add',
			text : '添加',
			handler : function() {
				url = "ir/addIr.action";
				$('#addForm').form('clear');
				dd.dialog('open');//打开添加菜单窗口
			}
		},'-',{
			plain : true,
			iconCls : 'icon-edit',
			text : '编辑',
			handler : function() {
				var row = datagrid.datagrid('getSelected');
				if (row) {
					url = 'ir/updateIr.action?id=' + row.id;
					$('#addForm').form('load',row);
					$('input[type=file]').validatebox('disableValidation');
					$('#dd').dialog('open').dialog('setTitle', '编辑');//打开添加窗口
				}
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var row = datagrid.datagrid('getSelected');
				if(row) {
					$.messager.confirm('系统提示', '您想要删除该数据吗？', function(r){
						if (r){
							$.post('ir/deleteIr.action',{id:row.id},function(result){
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
				} else {
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
				$.messager.alert('系统提示','点击各列对应列的表头，可以排序！','info');
			}
		} ],
		onLoadSuccess : function(data) {
			$('.formaLink').linkbutton({
				plain : true,
			    iconCls: 'icon-remove',
			    text : '下载'
			}); 
		}

	});

	dd = $('#dd').dialog({
		width : 300,
		height : 280,
		modal : true,
		closed : true,
		title : '添加',
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
				saveForm();
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

function saveForm() {
	$('#addForm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			$('#addForm').form('clear');
			$('#dd').dialog('close');
			
			var data = eval('(' + data + ')'); // change the JSON string to javascript object    
			var message = data.message;
			if(data.success) {
				datagrid.datagrid('reload');
				$.messager.alert('系统提示',message,'info');
			} else {
				$.messager.alert('系统提示',message,'error');
			}
		}
	});
}

function downloadIr(irId, index) {
	window.open('ir/downloadIr.action?id='+irId);
};

</script>

</head>
<body>
	<div id="cc" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',title:'按条件查询',split:true,collapsed:true" href="ir/selectHtml.html" style="height:80px;"></div>
		<div data-options="region:'center'">
			<table id="dg"></table>
		</div>
	</div>

	<div id="dd">
		<form id="addForm" class="form" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>基本信息</legend>
				<table style="width: 100%;" class="table">
					<tbody>
						<tr>
							<td style="text-align:right">标题</td>
							<td><input data-options="required:true,validType:['length[0,50]']" class="easyui-validatebox" name="title"></td>
						</tr>
						<tr>
							<td style="text-align:right">文件</td>
							<td><input data-options="required:true" class="easyui-validatebox" name="file" type="file"></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>
	
</body>

</html>
