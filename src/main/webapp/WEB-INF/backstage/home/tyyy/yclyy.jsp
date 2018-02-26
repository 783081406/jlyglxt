<%--
  Created by IntelliJ IDEA.
  已处理预约
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
    <title>已处理预约</title>
    <meta charset="utf-8"/>
    <meta name="description" content="体验预约->已处理预约"/>
    <meta name="author" content="陈彩君"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>backstage/home/css/mycss.css"/><!--自定义css-->
    <script type="text/javascript" src="<%=basePath %>easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<table id="dg" title="已处理信息" class="easyui-datagrid" style="width:980px;height:340px"
       url="<%=basePath %>bespeakaction/getAllHandleInformation.action" pagination="true" rownumbers="true"
       fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="name" width="100">姓名</th>
        <th field="phone" width="150">手机</th>
        <th field="ename" width="100">家长姓名</th>
        <th field="eage" width="60">家长年龄</th>
        <th field="handleUser" width="100">处理人</th>
        <th field="submitDate" width="150">提交时间</th>
        <th field="handleDate" width="150">处理时间</th>
    </tr>
    </thead>
</table>
</body>
</html>
