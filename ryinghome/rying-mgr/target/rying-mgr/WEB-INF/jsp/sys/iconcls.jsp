<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<c:forEach items="${iconList }" var="icon">
		<a href="#" onclick="changeIcon(this);" class="easyui-linkbutton" data-options="plain:true,iconCls:'${icon }'"></a>  
	</c:forEach>
	<script type="text/javascript">
		function changeIcon(obj) {
			var options = $(obj).linkbutton('options');
			$('#icon').linkbutton({    
			    iconCls: options.iconCls   
			}); 
			$('#iconCls').val(options.iconCls);
			tooltip.tooltip('hide');
		}
	</script>
</body>
