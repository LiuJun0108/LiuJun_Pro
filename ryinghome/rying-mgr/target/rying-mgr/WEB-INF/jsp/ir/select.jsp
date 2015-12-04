<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$('#searchBtn').click(function(){
		if($('#selectForm').form('validate')) {
			var years = $('#years').val();
			$('#dg').datagrid({
				queryParams:{
					years : years
				}
			});
		}
	});
	
	$('#clearBtn').click(function() {
		$('#years').val('').focus();
		$('#dg').datagrid('load',{});
	});
});

$.extend($.fn.validatebox.defaults.rules, {    
    years: {    
        validator: function(value, param){  
        	var reg = /^\d{4}$/;
        	return value.match(reg);
        },    
        message: '请输入正确的年份'   
    }    
});
</script>
<form id="selectForm" class="form" method="post">
	<table class="table" style="text-align:center">
		<tr>
			<td>
				年份：
				<input class="easyui-validatebox" data-options="validType:'years'" id="years" name="years" >
				<a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				<a id="clearBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-no'">清空</a>  
			</td>
		</tr>
	</table>
</form>
