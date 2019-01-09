<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理分区</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function doAdd(){
		$('#add_sub_area_window').window("open");
	}
	
	// 
	function doEdit(){
		alert("修改...");
	}
	
	function doDelete(){
		alert("删除...");
	}
	
	function doSearch(){
		$('#search_window').window("open");
	}
	
	// 导出数据到Excel表格
	function doExport(){
		window.location.href = "${pageContext.request.contextPath}/SubAreaAction_exportXls";
	}
	
	// 把Excel表格数据导入到数据库
	function doImport(){
		alert("导入...");
	}
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList: [10, 15, 20],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/SubAreaAction_queryPage",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加、修改分区
		$('#add_sub_area_window').window({
	        title: '添加修改分区',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 查询分区
		$('#search_window').window({
	        title: '查询分区',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 查询按钮
		$("#search_btn").click(function(){
			// 将表单数据转化为json对象
		    var jsonObj = $("#search_condition_form").serializeJson();
			console.info(jsonObj);
			// 重新发送ajax请求，条件查询分区
			$("#grid").datagrid("load", jsonObj);
			// 关闭窗口
			$("#search_window").window("close");
		});
		
		// 保存分区
		$("#save_sub_region").click(function() {
			var res = $("#add_sub_area_form").form("validate");
			if (res) {
				$("#add_sub_area_form").submit();
			}
		});
		
		// 将表单序列化为json对象
		$.fn.serializeJson = function() {  
            var serializeObj = {};  
            var array = this.serializeArray();
            $(array).each(function() {  
                if (serializeObj[this.name]) {  
                    if ($.isArray(serializeObj[this.name])) {  
                        serializeObj[this.name].push(this.value);  
                    } else {  
                        serializeObj[this.name] = [serializeObj[this.name],this.value];  
                    }  
                } else {  
                    serializeObj[this.name] = this.value;   
                }
            });
            return serializeObj;  
        }; 
	});

	function doDblClickRow(){
		alert("双击表格数据...");
	}
	
	// 工具栏
	var toolbar = [ {
		id : 'button-search',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',	
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	},{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-import',
		text : '导入',
		iconCls : 'icon-redo',
		handler : doImport
	},{
		id : 'button-export',
		text : '导出',
		iconCls : 'icon-undo',
		handler : doExport
	}];
	
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'showid',
		title : '分区编号',
		width : 120,
		align : 'center',
		formatter : function(data, row, index){
			return row.id;
		}
	},{
		field : 'province',
		title : '省',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.province;
		}
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.city;
		}
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.district;
		}
	}, {
		field : 'addressKey',
		title : '关键字',
		width : 120,
		align : 'center'
	}, {
		field : 'startNum',
		title : '起始号',
		width : 100,
		align : 'center'
	}, {
		field : 'endNum',
		title : '终止号',
		width : 100,
		align : 'center'
	} , {
		field : 'single',
		title : '单双号',
		width : 100,
		align : 'center'
	} , {
		field : 'position',
		title : '位置',
		width : 200,
		align : 'center'
	} ] ];
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	
	<!-- 添加分区 -->
	<div class="easyui-window" title="添加分区" id="add_sub_area_window" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save_sub_region" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="add_sub_area_form" action="${pageContext.request.contextPath }/SubAreaAction_addSubArea" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">分区信息</td>
					</tr>
					<!-- 分区属于区域 -->
					<tr>
						<td>选择区域</td>
						<td>
							<input class="easyui-combobox" name="region.id" data-options="valueField: 'id', textField: 'name', mode: 'remote', url: '${pageContext.request.contextPath }/RegionAction_getRegionListByAjax'" />  
						</td>
					</tr>
					<!-- 分区信息 -->
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addressKey" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>起始号</td>
						<td><input type="text" name="startNum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>终止号</td>
						<td><input type="text" name="endNum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单双号</td>
						<td>
							<select class="easyui-combobox" name="single" style="width:150px;">  
							    <option value="0">单双号</option>  
							    <option value="1">单号</option>  
							    <option value="2">双号</option>  
							</select> 
						</td>
					</tr>
					<tr>
						<td>位置信息</td>
						<td><input type="text" name="position" class="easyui-validatebox" required="true" style="width:250px;" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 查询分区 -->
	<div class="easyui-window" title="查询分区" id="search_window" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="search_condition_form">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="region.province"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="region.city"/></td>
					</tr>
					<tr>
						<td>区（县）</td>
						<td><input type="text" name="region.district"/></td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addressKey"/></td>
					</tr>
					<tr>
						<td colspan="2"><a id="search_btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>