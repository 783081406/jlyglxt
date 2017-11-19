<%--
  Created by IntelliJ IDEA.
  User: ccjjltx
  Date: 2017/11/19
  Time: 14:00
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
    <title>宣传栏</title>
    <meta charset="utf-8"/>
    <meta name="description" content="前端功能->宣传栏"/>
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
<table id="dg" title="宣传栏记录" class="easyui-datagrid" style="width:940px;height:600px" url="content.json"
       pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th width="300" data-options="field:'bpath',align:'center',formatter:imgFormatter">图片</th>
        <th field="btitle" width="150">标题</th>
        <th field="bcontent" width="300">内容</th>
    </tr>
    </thead>
</table>
<script>
    function imgFormatter(value, row, index) {
        return "<img width='320px' height='100px' src='" + row.bpath + "'/>";
    }
</script>
</body>
</html>
