<%--
  Created by IntelliJ IDEA.
  未处理预约
  User: ccjjltx
  Date: 2017/10/16
  Time: 23:58
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
    <title>未处理预约</title>
    <meta charset="utf-8"/>
    <meta name="description" content="体验预约->已处理预约"/>
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
<table id="dg" title="已处理信息" class="easyui-datagrid" style="width:980px;height:340px"
       url="<%=basePath %>bespeakaction/getAllUnhandleInformation.action" pagination="true" rownumbers="true"
       toolbar="#toolbar" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="name" width="100">姓名</th>
        <th field="phone" width="150">手机</th>
        <th field="ename" width="100">家长姓名</th>
        <th field="eage" width="60">家长年龄</th>
        <th field="submitDate" width="150">提交时间</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="handle()">处理</a>
    </div>
</div>
<script>
    function handle() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('警告', '是否确定处理本行数据?', function (r) {
                //如果选择确定，执行if里面语句
                if (r) {
                    //post提交
                    $.post('<%=basePath %>bespeakaction/handleInformation.action', {bid: row.bid}, function (result) {
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
            });
        }
    }
</script>
</body>
</html>

