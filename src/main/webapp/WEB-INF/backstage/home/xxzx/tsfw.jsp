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
    <link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>backstage/home/css/mycss.css"/><!--自定义css-->
    <script type="text/javascript" src="<%=basePath %>easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>easyui/locale/easyui-lang-zh_CN.js"></script>
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
            <label>名字:</label>
            <input id="box" name="ename" style="width:173px;">
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
            <input name="remark" class="easyui-validatebox">
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
    ///////////////////////////////////////////////
    var url;
    function newss() {
        $('#dlg').dialog('open').dialog('setTitle', '新房间信息');
        //清空表单，来显示空表单
        $('#fm').form('clear');
        //提交数据处理的URL
        url = '<%=basePath %>sserviceaction/addInformation.action';
    }

    //修改
    function editss() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', '编辑信息');
            //加载点击那一行的数据
            $('#fm').form('load', row);
            //提交数据处理的URL
            url = '<%=basePath %>sserviceaction/updateInformation.action?ssid=' + row.ssid;
        }
    }

    //提交功能
    function savess() {
        $('#fm').form('submit', {
            url: url,
            onSubmit: function () {
                //验证数据是否必填项完整
                return $(this).form('validate');
            },
            success: function (result) {
                //返回的是json数据，这里解析出来
                var result = eval('(' + result + ')');
                if (result.success) {//如果返回成功信息
                    $('#dlg').dialog('close');		// 关闭window
                    $('#dg').datagrid('reload');	//重新加载数据
                } else {//返回是失败信息
                    $.messager.show({//弹出提示框来说明插入失败以及返回的信息
                        title: '错误提示',
                        msg: result.msg
                    });
                }
            }
        });
    }

    //删除功能
    function removess() {
        //得到那一行的数据，如果没选为空，不能进入if语句里面
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('警告', '是否确定删除本行数据?', function (r) {
                //如果选择确定，执行if里面语句
                if (r) {
                    //post提交
                    $.post('<%=basePath %>sserviceaction/removeInformation.action', {ssid: row.ssid}, function (result) {
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
