<%--
  Created by IntelliJ IDEA.
  创建账户
  User: ccjjltx
  Date: 2017/10/16
  Time: 23:35
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
    <title>创建账户</title>
    <meta charset="utf-8"/>
    <meta name="description" content="员工中心->创建账户"/>
    <meta name="author" content="陈彩君"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>backstage/home/css/mycss.css"/><!--自定义css-->
    <script type="text/javascript" src="<%=basePath %>easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<table id="dg" title="账户列表" class="easyui-datagrid" style="width:700px;height:400px"
       url="<%=basePath %>useraction/getUser.action"
       toolbar="#toolbar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="userName" width="100">用户名</th>
        <th field="password" width="100">密码</th>
        <th field="uType" width="100">类型</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加账户</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改账户</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">删除账户</a>
    </div>
    <div>
        <div style="padding:0 0 0 7px;color:#333;">
            查询账号：<input type="text" class="textbox" name="userName" style="width:150px;height:25px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search();">查询</a>
        </div>
    </div>
</div>
<!--新账户window-->
<div id="dlg" class="easyui-dialog" style="width:400px;height:250px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="ftitle">账户信息</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>用户名:</label>
            <input name="userName" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>密码:</label>
            <input name="password" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>类型:</label>
            <select name="uType" class="easyui-combobox" style="width:100px;" required="required">
                <option value="管理员">管理员</option>
                <option value="普通">普通</option>
            </select>
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
                userName: $.trim($('input[name="userName"]').val())
            });
        }
    };
    //////////////////////////////////////////////
    var url;
    function newUser() {
        $('#dlg').dialog('open').dialog('setTitle', '新账户');
        //清空表单，来显示空表单
        //$('#fm').form('clear');

        //提交数据处理的URL
        url = '<%=basePath %>useraction/saveUser.action';
    }

    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', '编辑账户');
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
