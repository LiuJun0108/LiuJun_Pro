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
		url : 'sysUser/datagrid.sys',
		pagination : true,
		pageSize : 10,
		pageList : [10,20,30,40,50],
		fit : true,
		rownumbers : true,
		striped : false,
		singleSelect : true,
		idField : 'id',
		columns : [ [ {
			title : '用户名',
			field : 'username',
			sortable : true
		}, {
			title : '姓名',
			field : 'name',
			sortable : true
		}, {
			title : '手机号',
			field : 'phone',
			sortable : true
		}, {
			title : '电子邮件',
			field : 'email',
			sortable : true
		}, {
			field : 'createtime',
			title : '创建时间',
			align : 'center',
			sortable : true
		}, {
			field : 'modifytime',
			title : '最后一次修改时间',
			align : 'center',
			sortable : true
		}, {
			field : 'id',
			title : '操作',
			align : 'center',
			formatter: function(value,row,index){
				return '<a href="#" class="formaLink" onclick="openWin('+value+','+ index +');"></a>';
			}
		} ] ],
		toolbar : [ {
			plain : true,
			iconCls : 'icon-add',
			text : '添加用户',
			handler : function() {
				url = "sysUser/addSysUser.sys";
				$('#addSysUserForm').form('clear');
				dd.dialog('open').dialog('setTitle', '添加用户');//打开添加菜单窗口
			}
		},'-',{
			plain : true,
			iconCls : 'icon-edit',
			text : '编辑用户',
			handler : function() {
				var row = datagrid.datagrid('getSelected');
				if (row) {
					url = 'sysUser/updateSysUser.sys?id=' + row.id;
					$('#addSysUserForm').form('load',row);
					$('#dd').dialog('open').dialog('setTitle', '编辑用户');//打开添加窗口
				}
			}
		}, '-', {
			text : '删除用户',
			iconCls : 'icon-remove',
			handler : function() {
				var row = datagrid.datagrid('getSelected');
				if(row) {
					$.messager.confirm('系统提示', '您想要删除该用户吗？', function(r){
						if (r){
							$.post('sysUser/destroySysUser.sys',{id:row.id},function(result){
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
				$.messager.alert('系统提示','点击各列对应列的表头，可以排序！','info');
			}
		} ],
		onLoadSuccess : function(data) {
			$('.formaLink').linkbutton({
				plain : true,
			    iconCls: 'icon-add',
			    text : '添加角色'
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
				$('#addSysUserForm').form('submit', {
					url : url,
					onSubmit : function() {
						return $(this).form('validate');
					},
					success : function(data) {
						var data = eval('(' + data + ')'); // change the JSON string to javascript object    
						$.messager.alert('系统提示', data.message, 'info');
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
		} ]
	});
	
	
});

function openWin(userId, index) {
	datagrid.datagrid('selectRow',index);//选中datagrid记录
	$('#roles_dd').dialog({    
	    title: '角色列表',    
	    width: 400,    
	    height: 400,    
	    closed: false,    
	    cache: false,    
	    modal: true,
		buttons:[{
			iconCls : 'icon-save',
			text:'保存',
			handler:function(){
				var sysUser = datagrid.datagrid('getSelected');
				var sysUserId = sysUser.id;

				var roles = $('#roles_dg').datagrid('getChecked');
				var roleIds = '';
				$.each(roles, function(index, item) {
					var roleId = item.id;
					roleIds += roleId + ',';
				});
				roleIds = roleIds.substring(0, roleIds.length-1);
									
				$.post('sysUser/addRoles.sys', {
					id : userId,
					roleIds : roleIds
				}, function(data) {
					if(data) {
						if(data.success) {
							$('#roles_dd').dialog('close');
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
			handler:function(){
				$('#dg').datagrid('clearChecked');
				$('#roles_dd').dialog('close');
			}
		} ],
		onOpen : function() {
			var userData = datagrid.datagrid('getData');
			var rows = userData.rows;
			
			$.each(rows,function(index, item){
				var id = item.id;
				if(id == userId) {
					var username = item.username;
					var name = item.name;
					$('#roles_dd .fitem #username').text(username);
					$('#roles_dd .fitem #name').text(name);
				}
			});
			openDataGridOfRole();
		}
	}); 

};

function openDataGridOfRole() {
	$('#roles_dg').datagrid({
		idField : 'id',
		url : 'role/datagridWithNoPagination.sys',
		rownumbers : true,
		columns : [ [ {
			field : 'id',
			checkbox : true
		}, {
			field : 'name',
			title : '角色名'
		}, {
			field : 'createtime',
			title : '创建时间'
		}, {
			field : 'modifytime',
			title : '最后一次修改时间'
		} ] ],
		onLoadSuccess : function(data) {
			$('#roles_dg').datagrid('clearChecked');
			var sysUser = datagrid.datagrid('getSelected');
			var sysUserId = sysUser.id;
			
			$.post('role/findRolesBySysUserId.sys', {
				sysUserId : sysUserId
			}, function(data) {
				if(data) {
					$.each(data, function(index, item){
						var id = item.id;
						$('#roles_dg').datagrid('selectRecord', id);
					});
				}
			},'json');
		}
	});
};
</script>

</head>
<body>
	<div id="cc" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',title:'按条件查询',split:true,collapsed:true" href="sysUser/selectSysUser.sys" style="height:120px;background:#eee;"></div>
		<div data-options="region:'center'">
			<table id="dg"></table>
		</div>
	</div>

	<div id="dd">
		<form id="addSysUserForm" class="form" method="post">
			<fieldset>
				<legend>用户基本信息</legend>
				<table style="width: 100%;" class="table">
					<tbody>
						<tr>
							<td style="text-align:right">用户名</td>
							<td><input data-options="required:true,validType:['length[0,20]']" class="easyui-validatebox" name="username"></td>
						</tr>
						<tr>
							<td style="text-align:right">密码</td>
							<td><input data-options="required:true,validType:['length[0,20]']" class="easyui-validatebox" name="password"></td>
						</tr>
						<tr>
							<td style="text-align:right">姓名</td>
							<td><input data-options="required:true,validType:['length[0,10]']" class="easyui-validatebox" name="name"></td>
						</tr>
						<tr>
							<td style="text-align:right">手机号</td>
							<td><input data-options="required:true,validType:['length[0,15]','phone']" class="easyui-validatebox" name="phone"></td>
						</tr>
						<tr>
							<td style="text-align:right">电子邮件</td>
							<td><input data-options="validType:['length[0,20]','email']" class="easyui-validatebox" name="email"></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>
	
	<div id="roles_dd">
		<div class="fitem">
			<fieldset>
				<legend>用户信息</legend>
				<table width="100%">
					<tr>
						<td width="50%"><label>用户名:</label><label id="username" style="color:red">111111</label></td>
						<td width="50%"><label>姓名:</label><label id="name" style="color:red">12</label></td>
					</tr>
				</table>
			</fieldset>
		</div>
		<table id="roles_dg"></table>
	</div>
</body>

</html>
