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
		url : 'role/datagrid.sys',
		pagination : true,
		pageSize : 20,
		pageList : [20,40,60,80,100],
		fit : true,
		rownumbers : true,
		striped : false,
		singleSelect : true,
		columns : [ [ {
			title : '角色名',
			field : 'name',
			sortable : true,
			width : '100'
		}, {
			field : 'createtime',
			title : '创建时间',
			align : 'center',
			sortable : true,
			width : '150'
		}, {
			field : 'modifytime',
			title : '最后一次修改时间',
			align : 'center',
			sortable : true,
			width : '150'
		}, {
			field : 'id',
			title : '操作',
			align : 'center',
			width : '150',
			formatter: function(value,row,index){
				return '<a href="#" class="formaLink" onclick="openWin('+value+','+ index +');"></a>';
			}
		} ] ],
		toolbar : [ {
			plain : true,
			iconCls : 'icon-add',
			text : '添加角色',
			handler : function() {
				url = "role/addRole.sys";
				dd.dialog('open').dialog('setTitle',"添加角色");//打开添加菜单窗口
			}
		},'-',{
			plain : true,
			iconCls : 'icon-edit',
			text : '编辑角色',
			handler : function() {
				var row = datagrid.datagrid('getSelected');
				if (row) {
					url = 'role/updateRole.sys?id=' + row.id;
					$('#dd').dialog('open').dialog('setTitle', '编辑角色');//打开添加窗口
					$('#addRoleForm').form('load',row);
				}else {
					$.messager.show({
		                title:'操作提示',
		                msg:'请选择一条数据进行编辑操作',
		                showType:'show'
		            });
				}
			}
		}, '-', {
			text : '删除角色',
			iconCls : 'icon-remove',
			handler : function() {
				var row = datagrid.datagrid('getSelected');
				if(row) {
					$.messager.confirm('系统提示', '您想要删除该角色吗？', function(r){
						if (r){
							$.post('role/destroyRole.sys',{id:row.id},function(result){
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
		                title:'操作提示',
		                msg:'请选择一条数据进行删除操作',
		                showType:'show'
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
			    iconCls: 'icon-add',
			    text : '添加资源'
			}); 
		}
	});

	dd = $('#dd').dialog({
		width : 300,
		height : 180,
		modal : true,
		closed : true,
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
				$('#addRoleForm').form('submit', {
					url : url,
					onSubmit : function() {
						return $(this).form('validate');
					},
					success : function(data) {
						var data = eval('(' + data + ')'); // change the JSON string to javascript object    
						
						$.messager.alert('系统提示',data.message,'info');
						if (data.success) {
							datagrid.datagrid('reload');
							$('#dd').dialog('close');
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
		} ],
		onClose : function() {
			$('#addRoleForm').form('clear');
		}
	});
});

function openWin(roleId, index) {
	datagrid.datagrid('selectRow',index);//选中datagrid记录
	$('#resources_dd').dialog({    
	    title: '资源列表',    
	    width: 400,    
	    height: 400,    
	    closed: false,    
	    cache: false,    
	    modal: true,
		buttons:[{
			iconCls : 'icon-save',
			text:'保存',
			handler:function(){
				var roleRow = datagrid.datagrid('getSelected');
				var roleId = roleRow.id;

				var res = $('#resources_tt').tree('getChecked');
				var reIds = '';
				$.each(res, function(index, item) {
					var reId = item.id;
					if(reId != null) {
						reIds += reId + ',';
					}
				});
				reIds = reIds.substring(0, reIds.length-1);
				
				$.post('role/addResources.sys', {
					id : roleId,
					reIds : reIds
				}, function(data) {
					if(data) {
						if(data.success) {
							$('#resources_dd').dialog('close');
							$.messager.alert('系统提示',data.message,'info');
						}else {
							$.messager.alert('系统提示',data.message,'error');
						}
					}
				},'json');
			}
		}, {
			iconCls : 'icon-cancel',
			text:'关闭',
			handler : function() {
				$('#dg').datagrid('clearChecked');
				$('#resources_dd').dialog('close');
			}
		} ],
		onOpen : function() {
			var row = datagrid.datagrid('getSelected');
			$('#resources_dd .fitem #name').text(row.name);
			
			$('#resources_tt').tree({   
				checkbox : true,
			    url:'resources/tree.sys',
			    onLoadSuccess : function() {
					$.post('role/findResourcesByRoleId.sys', {
						roleId : roleId
					}, function(data) {
						if(data) {
							$.each(data, function(index, item) {
								var id = item.id;
								var node = $('#resources_tt').tree('find', id);
								if(node != null) {
									$('#resources_tt').tree('check', node.target);
								}
							});
						}
					},'json');
			    }
			}); 
		}
	}); 

};

</script>

</head>
<body>
	<table id="dg"></table>
	
	<div id="dd">
		<form id="addRoleForm" class="form" method="post">
			<fieldset>
				<legend>角色基本信息</legend>
				<table style="width: 100%;" class="table">
					<tbody>
						<tr>
							<td style="text-align:right">用户名</td>
							<td><input data-options="required:true,validType:['length[0,20]']" class="easyui-validatebox" name="name"></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>
	
	<div id="resources_dd">
		<div class="fitem">
			<fieldset>
				<legend>角色信息</legend>
				<table width="100%">
					<tr>
						<td><label>角色名:</label><label id="name" style="color:red"></label></td>
					</tr>
				</table>
			</fieldset>
		</div>
		<ul id="resources_tt"></ul>
	</div>
</body>

</html>
