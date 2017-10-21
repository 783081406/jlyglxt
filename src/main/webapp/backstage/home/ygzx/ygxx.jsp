<%--
  Created by IntelliJ IDEA.
  员工信息中心
  User: ccjjltx
  Date: 2017/10/16
  Time: 23:49
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
    <title>员工信息</title>
    <meta charset="utf-8"/>
    <meta name="description" content="员工中心->员工信息"/>
    <meta name="author" content="陈彩君"/>
    <link rel="stylesheet" type="text/css" href="../../../easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="../../../easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="../../../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../../../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../../easyui/locale/easyui-lang-zh_CN.js"></script>
    <style type="text/css">
        #fm {
            margin: 0;
            padding: 10px 30px;
        }

        .ftitle {
            font-size: 14px;
            font-weight: bold;
            color: #666;
            padding: 5px 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #ccc;
        }

        .fitem {
            margin-bottom: 5px;
        }

        .fitem label {
            display: inline-block;
            width: 80px;
        }
    </style>
</head>
<body>
<table id="dg" title="员工信息" class="easyui-datagrid" style="width:980px;height:400px"
       url="<%=basePath %>einformationaction/getAllInformation.action"
       toolbar="#toolbar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="name" width="100">姓名</th>
        <th field="idCard" width="250">身份证号码</th>
        <th field="sex" width="60">性别</th>
        <th field="address" width="250">家庭住址</th>
        <th field="pType" width="100">人员类型</th>
        <th field="hiredate" width="130">入职时间</th>
        <th field="education" width="100">学历</th>
        <th field="birthday" width="130">出生日期</th>
        <th field="userName" width="100">用户账号</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">删除</a>
    </div>
    <div>
        <div style="padding:0 0 0 7px;color:#333;">
            查询姓名：<input type="text" class="textbox" name="name" style="width:150px;height:25px">
            用户账号:<input type="text" class="textbox" name="userName" style="width:150px;height:25px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search();">查询</a>
        </div>
    </div>
</div>
<!--//////////////////////////查询的search方法里面传入参数还没修改///////////////////////////////-->
<!--添加的window-->
<div id="dlg" class="easyui-dialog" style="width:400px;height:250px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="ftitle">员工信息</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>姓名:</label>
            <input name="name" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>身份证号码:</label>
            <input name="idCard" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>性别:</label>
            <input name="sex" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>家庭住址:</label>
            <input name="address" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>人员类型:</label>
            <input name="pType" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>入职时间:</label>
            <input name="hiredate" class="easyui-datebox" required="required">
        </div>
        <div class="fitem">
            <label>学历:</label>
            <input name="education" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>出生日期:</label>
            <input name="birthday" class="easyui-datebox" required="required">
        </div>
        <div class="fitem">
            <label>用户账号:</label>
            <input name="userName" class="easyui-combobox" valueField="id" , textField:="user" , url="content.json" ,
                   required="required">
        </div>
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
    ////////////////////////////////////////////////
    //搜索功能
    obj = {
        search: function () {
            $('#dg').datagrid('load', {
                name: $.trim($('input[name="name"]').val())
            });
        }
    };
    //////////////////////////////////////////////
    var url;
    function newUser() {
        $('#dlg').dialog('open').dialog('setTitle', '新员工');
        //清空表单，来显示空表单
        $('#fm').form('clear');
        //提交数据处理的URL
        url = '<%=basePath %>useraction/saveUser.action';
    }

    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', '编辑员工信息');
            //加载点击那一行的数据
            $('#fm').form('load', row);
            //提交数据处理的URL
            url = '<%=basePath %>useraction/updateUser.action?id=' + row.id;
        }
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
                    $.post('<%=basePath %>useraction/removeUser.action', {id: row.id}, function (result) {
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
