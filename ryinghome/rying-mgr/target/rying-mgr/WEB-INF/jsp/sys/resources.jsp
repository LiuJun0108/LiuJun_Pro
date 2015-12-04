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
var treegrid;
var dd;
var url;

$(function() {
	treegrid = $('#tt').treegrid({
		url : 'resources/treegrid.sys',
		idField : 'createtime',
		treeField : 'name',
		rownumbers : true,
		fit : true,
		columns : [ [ {
			title : '资源名称',
			field : 'name',
			width : 180,
			sortable : true
		}, {
			title : '类型',
			field : 'type',
			width : 50,
			resizable : false,
			align : 'center',
			formatter: function(value,row,index){
				if(value == 1){
					return "菜单";
				}else if(value == 2) {
					return "资源"
				}else {
					return "-";
				}
			}
		}, {
			title : 'action',
			field : 'action',
			width : 200
		}, {
			field : 'createtime',
			title : '创建时间',
			align : 'center',
			width : 130,
			sortable : true,
			resizable : false
		}, {
			field : 'modifytime',
			title : '最后一次修改时间',
			align : 'center',
			width : 130,
			sortable : true,
			resizable : false
		},{
			title : '描述',
			field : 'description',
			width : 100
		}, {
			field : 'menuId',
			hidden : true
		} ] ],
		toolbar : [ {
			plain : true,
			iconCls : 'icon-add',
			text : '添加资源',
			handler : function() {
				url = "resources/addResources.sys";
				dd.dialog('open').dialog('setTitle', '添加资源');//打开添加菜单窗口
			}
		},'-',{
			plain : true,
			iconCls : 'icon-edit',
			text : '编辑资源',
			handler : function() {
				update();
			}
		}, '-', {
			text : '删除资源',
			iconCls : 'icon-remove',
			handler : function() {
				del();
			}
		}, '-', {
			text : '帮助',
			iconCls : 'icon-help',
			handler : function() {
				$.messager.alert('系统提示','点击“菜单名称”、“创建时间”、“修改时间”对应列的表头<br>可以排序！','info');
			}
		} ]

	});

	dd = $('#dd').dialog({
		width : 600,
		height : 300,
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
				$('#addResourcesForm').form('submit', {
					url : url,
					onSubmit : function() {
						return $(this).form('validate');
					},
					success : function(data) {
						var data = eval('(' + data + ')'); // change the JSON string to javascript object    
						
						$.messager.alert('系统提示', data.message, 'info');
						if (data.success) {
							treegrid.treegrid('reload');
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
			$('#addResourcesForm').form('clear');
			$('#seq').numberspinner('setValue', 5);  
		}

	});

});

function update() {
	var row = treegrid.treegrid('getSelected');
	if (row) {
		if(row.type == 1) {
			$.messager.show({
                title:'操作提示',
                msg:'请选择《类型》为”资源”的记录进行编辑',
                showType:'show'
            });
			return false;
		}
		
		url = 'resources/updateResources.sys?id=' + row.id;
		$('#dd').dialog('open').dialog('setTitle', '编辑资源');//打开添加菜单窗口
		addFormLoadData(row);
	}
}

function del() {
	var row = treegrid.treegrid('getSelected');
	if(row) {
		if(row.type == 1) {
			$.messager.show({
                title:'操作提示',
                msg:'请选择《类型》为”资源”的记录进行删除',
                showType:'show'
            });
			return false;
		}
		$.messager.confirm('系统提示', '您确定想要删除该资源吗？', function(r){
			if (r){
				$.post('resources/destroyResources.sys',{id:row.id},function(result){
                    $.messager.alert('系统提示',result.message,'info');
                    if (result.success){
                        treegrid.treegrid('reload');    // reload the user data
                        $('#dd').dialog('close');
                    }
                },'json');
			}
		});
	}
}
function addFormLoadData(row) {
	$('#addResourcesForm').form('load',{
		'name' : row.name,
		'pid' : row.pId,
		'seq' : row.seq,
		'action' : row.action,
		'description' : row.description
	});
}
</script>
</head>

<body>
	<table id="tt"></table>
	
	<div id="dd">
		<form id="addResourcesForm" class="form" method="post">
			<fieldset>
				<legend>资源基本信息</legend>
				<table style="width: 100%;" class="table">
					<tbody>
						<tr>
							<th>资源名称</th>
							<td><input data-options="required:true,validType:['length[0,20]']" class="easyui-validatebox" name="name"></td>
							<th>资源图标</th>
							<td></td>
						</tr>
						<tr>
							<th>上级菜单</th>
							<td>
								<select id="pid" name="pid" class="easyui-combotree" data-options="url:'menu/comboboxTreeAllMenu.sys',required:true,valueField:'id',textField:'name'" style="width:150px;"></select>
								<script type="text/javascript">
									$('#pid').combotree({
										onBeforeSelect : function(node) {
											var t = $('#pid').combotree('tree');
											var isLeaf = $(t).tree('isLeaf',node.target);
											if(!isLeaf) {
												return false;
											} 
										}
									});
								</script>
							</td>
							<th>资源顺序</th>
							<td>
								<input id="seq" name="seq" class="easyui-numberspinner" data-options="required:true,value:5,min:1,max:20,editable:false">
							</td>
						</tr>
						<tr>
							<th>action</th>
							<td colspan="3"><input name="action" class="easyui-validatebox" data-options="required:true,validType:['length[0,50]']" style="width:350px"></td>
						</tr>
						<tr>
							<th>资源描述</th>
							<td colspan="3"><textarea name="description" cols="75" rows="2"></textarea></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>
