<%--
  Created by IntelliJ IDEA.
  信息查看
  User: ccjjltx
  Date: 2017/10/16
  Time: 23:55
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
    <title>信息查看</title>
    <meta charset="utf-8"/>
    <meta name="description" content="信息中心->信息查看"/>
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
<table id="dg" title="信息列表" class="easyui-datagrid" style="width:700px;height:400px"
       url="<%=basePath %>useraction/getUser.action"
       toolbar="#toolbar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="ename" width="100">名字</th>
        <th field="sex" width="100">性别</th>
        <th field="idcard" width="200">身份证号</th>
        <th field="phone" width="100">手机号</th>
        <th field="homeAddress" width="400">家庭住址</th>
    </tr>
    </thead>
</table>
<!--工具栏-->
<div id="toolbar">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-more" plain="true" onclick="movec()">查看</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newc()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removec()">删除</a>
    </div>
    <div>
        <div style="padding:0 0 0 7px;color:#333;">
            查询账号：<input type="text" class="textbox" name="userName" style="width:150px;height:25px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search();">查询</a>
        </div>
    </div>
</div>
<!--查看与修改的window-->
<div id="morecheck" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="ftitle">账户信息</div>
    <form id="moref" method="post" novalidate>
        <div class="fitem">
            <label>名字:</label>
            <input name="userName" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>性别:</label>
            <input name="sex" class="easyui-validatebox" required="required">
            <label>出生日期:</label>
            <input name="birthDate" class="easyui-datebox" required="required">
        </div>
        <div class="fitem">
            <label>身份证号:</label>
            <input name="idcard" class="easyui-validatebox" required="required">
            <label>手机号:</label>
            <input name="phone" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>家庭住址:</label>
            <input name="homeAddress" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>户籍住址:</label>
            <input name="originAddress" class="easyui-validatebox" required="required">
        </div>
        <table id="moret" title="家庭信息" class="easyui-datagrid">
            <thead>
            <tr>
                <th field="familyName" width="100">家属信息</th>
                <th field="relationship" width="100">关系</th>
                <th field="faddress" width="200">家庭地址</th>
                <th field="phone" width="100">联系电话</th>
            </tr>
            </thead>
        </table>
    </form>
</div>
<!--新账户window-->
<div id="dlg" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="ftitle">相关信息</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>名字:</label>
            <input name="userName" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>性别:</label>
            <input name="sex" class="easyui-validatebox" required="required">
            <label>出生日期:</label>
            <input name="birthDate" class="easyui-datebox" required="required">
        </div>
        <div class="fitem">
            <label>身份证号:</label>
            <input name="idcard" class="easyui-validatebox" required="required">
            <label>手机号:</label>
            <input name="phone" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>家庭住址:</label>
            <input name="homeAddress" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>户籍住址:</label>
            <input name="originAddress" class="easyui-validatebox" required="required">
        </div>
        <table id="te" class="easyui-datagrid">
            <tr>
                <th width="100">家属信息</th>
                <th width="100">关系</th>
                <th width="200">家庭地址</th>
                <th width="100">联系电话</th>
            </tr>
            <tr>
                <td><input name="familyName" class="easyui-validatebox" required="required"/></td>
                <td><input name="relationship" class="easyui-validatebox" required="required"/></td>
                <td><input name="faddress" class="easyui-validatebox" required="required"/></td>
                <td><input name="phone" class="easyui-validatebox" required="required"/></td>
            </tr>
        </table>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="moreadd()">添加</a>
    </form>
</div>
<!--提交与取消按钮-->
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>

<!--自定义javascript-->
<script type="text/javascript">
    var url;
    //搜索功能
    obj = {
        search: function () {
            $('#dg').datagrid('load', {
                userName: $.trim($('input[name="userName"]').val())
            });
        }
    };
    //增加家属信息功能
    function moreadd() {
        $('#te').append("<tr>" +
            "<td><input name='familyName' class='easyui-validatebox' required='required'/></td>" +
            "<td><input name='relationship' class='easyui-validatebox' required='required'/></td>" +
            "<td><input name='faddress' class='easyui-validatebox' required='required'/></td>" +
            "<td><input name='phone' class='easyui-validatebox' required='required'/></td>" +
            "</tr>");
    }
    //查看功能
    function morec() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#morecheck').dialog('open').dialog('setTitle', '详细信息');
            //加载form表单的数据
            $('#moref').form('load', '<%=basePath %>xx/xx.action?eId=' + row.eId);
            $('#moret').datagrid('load', '<%=basePath %>xx/xx.action?eId=' + row.eId);
            //提交数据处理的url
            url = '<%=basePath %>xx/xx.action?eId=' + row.eId;
        }
    }
    //添加功能
    function newc() {
        $('#dlg').dialog('open').dialog('setTitle', '添加信息');
        //清空表单，来显示空表单
        $('#fm').form('clear');
        $('#te tr').empty();
        //提交数据处理的URL
        url = '<%=basePath %>einformationaction/saveInformation.action';
    }
    //提交功能
    function saveUser() {
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
                    $('#morecheck').dialog('close');//关闭window
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
    function removeUser() {
        //得到那一行的数据，如果没选为空，不能进入if语句里面
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('警告', '是否确定删除本行数据?', function (r) {
                //如果选择确定，执行if里面语句
                if (r) {
                    //post提交
                    $.post('<%=basePath %>xx/xx.action', {eId: row.eId}, function (result) {
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
