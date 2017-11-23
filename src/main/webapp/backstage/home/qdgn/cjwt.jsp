<%--
  Created by IntelliJ IDEA.
  常见问题
  User: ccjjltx
  Date: 2017/10/16
  Time: 23:53
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
    <title>常见问题</title>
    <meta charset="utf-8"/>
    <meta name="description" content="前端功能->常见问题"/>
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
<table id="dg" style="width:1150px;height:540px"></table>
<div id="toolbar">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reelect()">重选</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="submitb()">提交</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newb()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editb()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeb()">删除</a>
    </div>
</div>
<!--添加的window-->
<div id="dlg" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="ftitle">数据</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>问题:</label>
            <input style="height: 90px" name="question" class="easyui-textbox" data-options="multiline:true"
                   required="required">
        </div>
        <div class="fitem">
            <label>回答:</label>
            <input style="height: 90px" name="answer" class="easyui-textbox" data-options="multiline:true"
                   required="required">
        </div>
    </form>
</div>
<!--提交与取消按钮-->
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveb()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>

<script>
    $(function () {
        $('#dg').datagrid({
            title: '宣传栏记录',
            url: '<%=basePath %>interlocutionaction/getAllInformation.action',
            toolbar: '#toolbar',
            fitColumns: true,
            rownumbers: true,
            pagination: true,
            nowrap: false,
            columns: [[
                {field: 'ck', checkbox: true, align: 'center', width: 80},
                {field: 'question', title: '问题', align: 'center', width: 220},
                {field: 'answer', title: '回答', align: 'center', width: 300}
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
        $.post('<%=basePath %>interlocutionaction/reelectInformation.action', {}, function (result) {
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
    //提交
    function submitb() {
        //得到所有选中的数据
        var checkedItems = $('#dg').datagrid('getChecked');
        //定义一个数组，用来存取数据
        var ids = [];
        $.each(checkedItems, function (index, item) {
            ids.push({name: 'qaids', value: item.qaid});
        });
        $.post('<%=basePath %>interlocutionaction/selectInformation.action', ids, function (result) {
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

    //添加
    function newb() {
        $('#dlg').dialog('open').dialog('setTitle', '新数据');
        //清空表单，来显示空表单
        $('#fm').form('clear');
        //提交数据处理的URL
        url = '<%=basePath %>interlocutionaction/saveInformation.action';
    }

    //修改
    function editb() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', '更新数据');
            //加载点击那一行的数据
            $('#fm').form('load', row);
            //提交数据处理的URL
            url = '<%=basePath %>interlocutionaction/updateInformation.action?qaid=' + row.qaid;
        }
    }

    //提交功能
    function saveb() {
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

</script>
</body>
</html>
