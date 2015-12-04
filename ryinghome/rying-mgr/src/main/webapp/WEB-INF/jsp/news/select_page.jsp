<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$('#btn').click(function(){
		if($('#selectForm').form('validate')) {
			var startCreateTime = $('#startCreateTime').datebox('getValue');
			var endCreateTime = $('#endCreateTime').datebox('getValue');
			
			$('#dg').datagrid({
				queryParams:{
					start : startCreateTime,
					end : endCreateTime
				}
			});
		}
	});
	
	$('#btn2').click(function() {
		$('#startCreateTime').datebox('clear');
		$('#endCreateTime').datebox('clear');
		$('#dg').datagrid('load',{});
	});
});
</script>
<form id="selectForm" class="form" method="post" action="news/datagrid">
<table class="table" style="text-align:center">
	<tr>
		<td>
			创建时间：
			<input class="easyui-datebox" id="startCreateTime" name="startCreateTime" data-options="showSeconds:true,editable:false" style="width:150px"><label style="font-size:12px"> - </label>
			<input class="easyui-datebox" id="endCreateTime" name="endCreateTime" data-options="showSeconds:true,editable:false" style="width:150px">
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			<a id="btn2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-no'">清空</a>  
		</td>
	</tr>
</table>
</form>
