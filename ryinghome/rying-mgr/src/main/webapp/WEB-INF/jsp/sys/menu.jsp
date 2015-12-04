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
var dialog;
var tooltip;

var url;

$(function() {
	treegrid = $('#treegrid').treegrid({
		url : 'menu/treegrid.sys',
		idField : 'id',
		treeField : 'name',
		fitColumns : true,
		rownumbers : true,
		fit : true,
		columns : [ [ {
			title : '菜单名称',
			field : 'name',
			sortable : true
		}, {
			title : '资源路径',
			field : 'url'
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
		},{
			title : '描述',
			field : 'description'
		}, {
			field : 'pid',
			hidden : true
		} ] ],
		toolbar : [ {
			plain : true,
			iconCls : 'icon-add',
			text : '添加菜单',
			handler : function() {
				url = "menu/addMenu.sys";
				dialog.dialog('open').dialog('setTitle','添加菜单');//打开添加菜单窗口
			}
		},'-',{
			plain : true,
			iconCls : 'icon-edit',
			text : '编辑菜单',
			handler : function() {
				var row = treegrid.treegrid('getSelected');
				if (row) {
					url = 'menu/updateMenu.sys?id=' + row.id;
					dialog.dialog('open').dialog('setTitle', '编辑菜单');//打开添加菜单窗口
					$('#addMenuForm').form('load',row);
					$('#icon').linkbutton({    
					    iconCls: row.iconCls   
					}); 
				} else {
		            $.messager.show({
		                title:'操作提示',
		                msg:'请选择一条数据进行编辑操作',
		                showType:'show'
		            });
				}
			}
		}, '-', {
			text : '删除菜单',
			iconCls : 'icon-remove',
			handler : function() {
				var row = treegrid.treegrid('getSelected');
				if(row) {
					var cfMsg;
					var children = row.children;
					if(children.length > 0) {
						cfMsg = '删除该菜单会级联删除其下所有子菜单，你确定进行该删除操作吗？';
					}else {
						cfMsg = '您想要删除该菜单吗？';
					}
					$.messager.confirm('系统提示', cfMsg, function(r){
						if (r){
							$.post('menu/destroyMenu.sys',{id : row.id}, function(result){
                                $.messager.alert('系统提示',result.message,'info');
	                            if (result.success){
	                                treegrid.treegrid('reload');
	                                $('#dialog').dialog('close');
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
				$.messager.alert('系统提示','点击“菜单名称”、“创建时间”、“修改时间”对应列的表头<br>可以排序！','info');
			}
		} ]

	});

	dialog = $('#dialog').dialog({
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
				saveMenu();
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				dialog.dialog('close');
			}
		} ],
		onClose : function() {
			$('#addMenuForm').form('clear');
			$('#seq').numberspinner('setValue', 5);  
			$('#icon').linkbutton({    
			    iconCls: 'icon-ok'   
			});
		}
	});
	
	
	tooltip = $('#dd').tooltip({
        content: $('<div></div>'),
        showEvent: 'click',
        onUpdate: function(content){
            content.panel({
                width: 300,
                border: false,
                href: 'iconcls/iconcls.html'
            });
        },
        onShow: function(){
            tooltip.tooltip('tip').unbind().bind('mouseenter', function(){
            	tooltip.tooltip('show');
            }).bind('mouseleave', function(){
            	tooltip.tooltip('hide');
            });
        }
    });
});

function saveMenu() {
	$('#addMenuForm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			var data = eval('(' + data + ')');     
			$.messager.alert('系统提示',data.message,'info');
			
			if (data.success) {
				treegrid.treegrid('reload');
				dialog.dialog('close');
			}
		}
	});
}
</script>
</head>

<body>
	<table id="treegrid"></table>
	
	<div id="dialog">
		<form id="addMenuForm" class="form" method="post">
			<fieldset>
				<legend>菜单基本信息</legend>
				<table class="table">
					<tbody>
						<tr>
							<th>菜单名称</th>
							<td><input name="name" class="easyui-validatebox" data-options="required:true,validType:['length[0,20]']"></td>
							<th>菜单图标</th>
							<td>
								<input id="iconCls" name="iconCls" type="hidden" value="icon-ok">
								<a id="icon" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'"></a>
								<a id="dd" href="javascript:void(0)">更换图标</a>
							</td>
						</tr>
						<tr>
							<th>上级菜单</th>
							<td>
								<select name="pid" class="easyui-combotree" data-options="url:'menu/rootMenu.sys',valueField:'id',textField:'name'" style="width:150px;"></select>  
							</td>
							<th>菜单顺序</th>
							<td>
								<input id="seq" name="seq" class="easyui-numberspinner" data-options="required:true,value:5,min:1,max:20,editable:false">
							</td>
						</tr>
						<tr>
							<th>菜单路径</th>
							<td colspan="3">
								<input name="url" class="easyui-validatebox" data-options="required:true,validType:['length[0,50]']" style="width: 350px">
							</td>
						</tr>
						<tr>
							<th>菜单描述</th>
							<td colspan="3">
								<textarea name="description" cols="75" rows="2"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>
