<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, {    
    phone: {    
        validator: function(value, param){
        	var reg = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;     
	        var r = value.match(reg);     
	        if(r==null){
	        	return false;
	        }else {
	        	return true;
	        }
        },
        message: '请输入有效有手机号码'   
    }    
});  

$(function(){
	$('#btn').click(function(){
		if($('#selectForm').form('validate')) {
			var username = $('#username').val();
			var name = $('#name').val();
			var phone = $('#phone').val();
			var email = $('#email').val();
			
			var startModifyTime = $('#startModifyTime').datetimebox('getValue');
			var endModifyTime = $('#endModifyTime').datetimebox('getValue');
			var startCreateTime = $('#startCreateTime').datetimebox('getValue');
			var endCreateTime = $('#endCreateTime').datetimebox('getValue');
			
			$('#dg').datagrid({
				queryParams:{
					username : username,
					name : name,
					phone : phone,
					email : email,
					startModifyTime : startModifyTime,
					endModifyTime : endModifyTime,
					startCreateTime : startCreateTime,
					endCreateTime : endCreateTime
				}
			});
		}
	});
});
</script>
<form id="selectForm" class="form" method="post" action="sys/sysUserAction_datagrid.action">
<table class="table" style="text-align:center">
	<tr>
		<td>
			<label style="width:80px;font-size:12px">用户名：</label>
			<input id="username" name="username" class="easyui-validatebox" data-options="validType:['length[0,20]']" />
			<label style="width:80px;font-size:12px">姓名： </label>
			<input id="name"  name="name" class="easyui-validatebox" data-options="validType:['length[0,10]']" />
			<label style="width:80px;font-size:12px">手机号：</label>
			<input id="phone" name="phone" class="easyui-validatebox" data-options="validType:['length[0,15]','phone']" />
			<label style="width:80px;font-size:12px">电子邮件： </label>
			<input id="email" name="email" class="easyui-validatebox" data-options="validType:['length[0,20]','email']" />
		</td>
	</tr>
	<tr>
		<td>
			<label style="width:80px;font-size:12px">修改时间：</label>
			<input class="easyui-datetimebox" id="startModifyTime" name="startModifyTime" data-options="showSeconds:true,editable:false" style="width:150px"><label style="font-size:12px"> - </label><input class="easyui-datetimebox" id="endModifyTime" name="endModifyTime" data-options="showSeconds:true,editable:false" style="width:150px">
			<label style="width:80px;font-size:12px">创建时间：</label>
			<input class="easyui-datetimebox" id="startCreateTime" name="startCreateTime" data-options="showSeconds:true,editable:false" style="width:150px"><label style="font-size:12px"> - </label><input class="easyui-datetimebox" id="endCreateTime" name="endCreateTime" data-options="showSeconds:true,editable:false" style="width:150px">
			</td>
		</tr>
		<tr>
			<td>
				<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
			</td>
		</tr>
	</table>
</form>
