<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" charset="utf-8">
	$(function() {
		$('#accordion').accordion({
			fit : true,
			onSelect : function(title, index) {
				selectAccordion(title);
			}
		});
	});
	
	function selectAccordion(title) {
		if (title == '系统配置') {
			findMenu('sysMenu','menu/listSysMenu.json');
		} else if (title == '功能菜单') {
			findMenu('funcMenu','menu/listUserFuncMenu.json')
		}
	}
	
	function findMenu(ulId,url) {
		$('#'+ulId).tree({
			url : url,
			lines : true,
			onClick : function(node) {
				if ($(node).tree('isLeaf', node.target)) {
					addTab(node);
				}
			},
			onDblClick : function(node) {
				if (node.state == 'closed') {
					$(this).tree('expand', node.target);
				} else {
					$(this).tree('collapse', node.target);
				}
			}
		});
	}
</script>

<div id="accordion" class="easyui-accordion">
	<c:if test="${isAdmin == true }">
		<div id="accordionItem1" title="系统配置"
			data-options="iconCls:'icon-save'"
			style="padding: 5px;">
			<ul id="sysMenu"></ul>
		</div> 
	</c:if>
	<div id="accordionItem2" title="功能菜单"
		data-options="iconCls:'icon-save'">
		<ul id="funcMenu"></ul>
	</div>
	<div id="accordionItem3" title="其它">其它</div>
</div>
