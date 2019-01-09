<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>easyui_05</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery/easyui-1.3.6/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery/easyui-1.3.6/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/easyui-1.3.6/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/easyui-1.3.6/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    $(function() {

    });
</script>
</head>
<body>
    <!-- 菜单 -->
    <a data-options="iconCls: 'icon-help', menu: '#mm'" class="easyui-menubutton">控制面板</a>
    <!-- 下拉菜单 -->
    <div id="mm">
        <div data-options="iconCls: 'icon-edit'">修改密码</div>
        <div>联系管理员</div>
        <div class="menu-sep"></div>
        <div>退出系统</div>
    </div>
</body>
</html>