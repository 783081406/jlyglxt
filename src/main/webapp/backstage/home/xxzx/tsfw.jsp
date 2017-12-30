<%--
  Created by IntelliJ IDEA.
  特殊服务
  User: ccjjltx
  Date: 2017/10/16
  Time: 23:57
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
    <title>特殊服务</title>
    <meta charset="utf-8"/>
    <meta name="description" content="信息中心->特殊服务"/>
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
<table id="dg" title="特殊服务列表" class="easyui-datagrid" style="width:800px;height:400px"
       url="<%=basePath %>sserviceaction/getAllInformation.action"
       toolbar="#toolbar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="ename" width="100">姓名</th>
        <th field="stype" width="150">类型</th>
        <th field="stime" width="250">周期</th>
        <th field="remark" width="250">备注</th>
    </tr>
    </thead>
</table>
<!--工具栏-->
<div id="toolbar">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newss()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editss()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removess()">删除</a>
    </div>
    <div>
        <div style="padding:0 0 0 7px;color:#333;">
            查询姓名：<input type="text" class="textbox" name="ename" style="width:150px;height:25px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search();">查询</a>
        </div>
    </div>
</div>
<!--添加的window-->
<div id="dlg" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="ftitle">添加信息</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>姓名:</label>
            <input name="ename" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>类型:</label>
            <input name="stype" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>周期:</label>
            <input name="stime" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>备注:</label>
            <input name="remark" class="easyui-validatebox" required="required">
        </div>
    </form>
</div>
<!--提交与取消按钮-->
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savess()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>

<!--自定义javascript-->
<script type="text/javascript">
    ////////////////////////////////////////////////
    //搜索功能
    obj = {
        search: function () {
            $('#dg').datagrid('load', {
                ename: $.trim($('input[name="ename"]').val())
            });
        }
    };
    //////////////////////////////////////////////
    //下拉功能
    $(function () {
        $('#box').combobox({
            valueField: 'eId',
            textField: 'ename',
            url: '<%=basePath %>elderaction/getIdElderName.action'
        })
    });
</script>
</body>
</html>
