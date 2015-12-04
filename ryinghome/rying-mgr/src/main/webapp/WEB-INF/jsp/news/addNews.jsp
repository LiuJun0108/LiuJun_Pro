<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="kindeditor-4.1.10/themes/default/default.css" />
<script charset="utf-8" src="kindeditor-4.1.10/kindeditor-min.js"></script>
<script charset="utf-8" src="kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : [
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|', 'emoticons', 'link']
		});
	});
	
	function checkForm() {
		if($('#addForm').form('validate')) {
			if(editor.isEmpty()) {
				$.messager.alert('系统提示','新闻内容不能为空','error',function() {
					editor.focus();
				});
				return false;
			}
			var htmlCount = editor.count();
			if(htmlCount > 20000) {
				$.messager.alert('系统提示','新闻内容0-2W字符','error',function() {
					editor.focus();
				});
				return false;
			}
			return true;
		}
		return false;
	}
</script>
</head>
<body>
	<form id="addForm" action="news/addNews.action" class="form" method="post" onsubmit="return checkForm();">
		<input type="hidden" name="id" value="${news.id }">
		<fieldset>
			<legend>基本信息</legend>
			<table style="width: 100%;" class="table">
				<tbody>
					<tr>
						<td style="text-align:right">新闻标题</td>
						<td>
							<input data-options="required:true,validType:['length[0,100]']" class="easyui-validatebox" name="title" value="${news.title}" style="width:500px">
							*必填！0-100个字符
						</td>
					</tr>
					<tr>
						<td style="text-align:right">新闻内容</td>
						<td><textarea name="content" rows="25" cols="100">${news.content}</textarea>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input id="addBtn" type="submit" value="添加"/>
							<input type="button" value="返回" onclick="window.history.back(-1);"/>
						</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
		<script type="text/javascript">
			var id = $('input[name="id"]').val();
			if(id.length > 0) {
				$('#addForm').attr('action','news/updateNews.action');
				$('#addBtn').val('修改');
			} else {
				$('input[name="id"]').val(0);
			}
		</script>
	</form>
</body>
</html>