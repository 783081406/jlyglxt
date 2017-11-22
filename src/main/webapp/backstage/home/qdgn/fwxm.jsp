<%--
  Created by IntelliJ IDEA.
  User: ccjjltx
  Date: 2017/10/16
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="description" content="前端功能->服务项目"/>
    <meta name="author" content="陈彩君"/>
    <link rel="stylesheet" type="text/css" href="../../../easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="../../../easyui/themes/default/easyui.css"/>
    <!--自定义css-->
    <link rel="stylesheet" type="text/css" href="../css/mycss.css"/>
    <script type="text/javascript" src="../../../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../../../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../../easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<table id="dg" style="width:980px;height:540px"></table>
<div id="toolbar">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reelect()">重选</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="submits()">提交</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="news()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edits()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removes()">删除</a>
    </div>
</div>

<script>
    function imgFormatter(value, row, index) {
        return "<img width='87px' height='87px' src='" + row.spath + "'/>";
    }
    $(function () {
        $('#dg').datagrid({
            title: '宣传栏记录',
            url: '<%=basePath %>serviceiaction/getAllInformation.action',
            toolbar: '#toolbar',
            fitColumns: true,
            rownumbers: true,
            pagination: true,
            nowrap: false,
            columns: [[
                {field: 'ck', checkbox: true, align: 'center', width: 80},
                {field: 'spath', title: '图片', align: 'center', formatter: imgFormatter, width: 120},
                {field: 'stitle', title: '标题', align: 'center', width: 120},
                {field: 'scontent', title: '内容', align: 'center', width: 300}
            ]],
            singleSelect: false,
            selectOnCheck: true,
            checkOnSelect: true
        });
    });

    ////////////////////工具栏//////////////////////
    var url;
    //重选
    function reelect() {
        $.post('<%=basePath %>serviceiaction/reelectInformation.action', {}, function (result) {
            if (result.success) {
                $('#dg').datagrid('reload');	// 重新加载数据
            } else {
                $.messager.show({	// 显示错误的信息
                    title: '错误提示',
                    msg: result.msg
                });
            }
        }, 'json');
    }

</script>
</body>
</html>
