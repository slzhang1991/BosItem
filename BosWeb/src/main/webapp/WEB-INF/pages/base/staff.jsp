<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Staff</title>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/default.css">
<!-- 导入jquery核心类库 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	// 查看取派员信息
	function doView() {
		alert("查看...");
	}
	
	// 添加取派员信息
	function doAdd() {
		$('#add_staff_window').window("open");
	}
	
	// 删除取派员
	function doDelete() {
		// 获取数据表格中选中的行，返回数组对象
		var rows = $("#grid").datagrid("getSelections");
		if (rows.length == 0) {
			// 没有选中记录，弹出提示
			$.messager.alert("提示信息", "请选择需要删除的取派员！", "warning");
		} else {
			// 选中了记录，弹出确认框
			$.messager.confirm("确认删除", "你确定要删除选中的取派员吗？", function(res) {
				if (res) {
					// 确定删除，获取所有选中的取派员的id
					var arr = new Array();
					for (var i = 0; i < rows.length; i++) {
						var staff = rows[i];
						var id = staff.id;
						// 将取派员的id放入数组
						arr.push(id);
					}
					// 将取派员id的数组转换为逗号分隔的字符串
					var ids = arr.join(",");
					// 发送请求，id组成的字符串作为参数
					window.location.href = "${pageContext.request.contextPath}/StaffAction_deleteBatch?ids=" + ids;
				}
			});
		}
	}
	
	// 恢复取派员信息
	function doRestore() {
		// 获取表格中选中的行，返回数组对象
		var rows = $("#grid").datagrid("getSelections");
		if (rows.length == 0) {
			$.messager.alert("提示信息", "请选择需要恢复的取派员！", "warning");
		} else {
			// 确认框
			$.messager.confirm("确认恢复", "你确定要恢复选中和取派员吗？", function(res) {
				if (res) {
					var arr = new Array();
					// 遍历选中的对象
					for (var i = 0; i < rows.length; i++) {
						var staff = rows[i];
						var id = staff.id;
						arr.push(id);
					}
					// 将id组成的数组转换成逗号分隔的字符串
					var ids = arr.join(",");
					// 发送请求，id组成的字符串作为参数
					window.location.href = "${pageContext.request.contextPath}/StaffAction_restoreBatch?ids=" + ids;
				}
			});
		}
	}
	
	// 修改取派员信息
	function doDblClickRow(rowIndex, rowData) {
		// 打开修改取派员窗口
		$("#edit_staff_window").window("open");
		// 回显数据
		$("#edit_staff_form").form("load", rowData);
	}
	
	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 取派员信息表格
		$('#grid').datagrid({
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList : [10, 15, 20],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/StaffAction_queryPage",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});

		// 添加取派员窗口
		$('#add_staff_window').window({
			title : '添加取派员',
			width : 400,
			height : 400,
			modal : true,
			shadow : true,
			closed : true,
			resizable : false
		});

		// 修改取派员窗口
		$("#edit_staff_window").window({
			title : "修改取派员",
			width : 400,
			height : 400,
			modal : true,
			shadow : true,
			closed : true,
			resizable : false
		});

		// 扩展手机号校验规则
		var reg = /^1[3|4|5|7|8|9][0-9]{9}/;
		$.extend($.fn.validatebox.defaults.rules, {
			telephone : {
				validator : function(value, param) {
					return reg.test(value);
				},
				message : "手机号有误！"
			}
		});

		// 保存取派员
		$("#save").click(function() {
			// 表单校验
			var res = $("#add_staff_form").form("validate");
			// 校验通过
			if (res) {
				// 提交表单
				$("#add_staff_form").submit();
			}
		});

		// 修改取派员
		$("#edit").click(function() {
			// 表单校验
			var res = $("#edit_staff_form").form("validate");
			if (res) {
				// 校验通过，提交表单
				$("#edit_staff_form").submit();
			}
		});

	});

	// 工具栏
	var toolbar = [ {
		id : 'button-view',
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}, {
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-redo',
		handler : doRestore
	} ];

	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "1") {
				return "有";
			} else {
				return "无";
			}
		}
	}, {
		field : 'delTag',
		title : '是否删除',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "0" || data == 0) {
				return "<font style='color: green'>正常使用</font>"
			} else {
				return "<font style='color: red'>已删除</font>"
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];

</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">
    <!-- 取派员列表 -->
	<div region="center" border="false">
		<table id="grid"></table>
	</div>
	
	<!-- 添加取派员信息 -->
	<div class="easyui-window" title="对收派员进行添加或者修改" id="add_staff_window" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;" split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
			</div>
		</div>

		<!-- 添加取派员窗口 -->
		<div region="center" style="overflow: auto; padding: 5px;" border="false">
			<form id="add_staff_form" action="${pageContext.request.contextPath }/StaffAction_add" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone" class="easyui-validatebox" required="true" data-options="validType: 'telephone'" /></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="hasPda" value="1" /> 是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard" class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
		
	<!-- 修改取派员窗口 -->
	<div class="easyui-window" title="对收派员进行添加或者修改" id="edit_staff_window" collapsible="false" minimizable="false" maximizable="false" style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;" split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" class="easyui-linkbutton" plain="true">更新</a>
			</div>
		</div>
		
		<div region="center" style="overflow: auto; padding: 5px;" border="false">
			<form id="edit_staff_form" action="${pageContext.request.contextPath }/StaffAction_edit" method="post">
				<input type="hidden" name="id" />
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone" class="easyui-validatebox" required="true" data-options="validType: 'telephone'" /></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="hasPda" value="1" /> 是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard" class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
