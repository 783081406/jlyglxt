<%--
  Created by IntelliJ IDEA.
  服务项目
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
    <link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>backstage/home/css/mycss.css"/><!--自定义css-->
    <script type="text/javascript" src="<%=basePath %>easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>easyui/locale/easyui-lang-zh_CN.js"></script>
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
<!--添加的window-->
<div id="dlg" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="ftitle">新数据</div>
    <form id="fm" method="post" enctype="multipart/form-data">
        <div class="fitem">
            <label>图片:</label>
            <input class="easyui-filebox" id="upload" name="upload"
                   data-options="prompt:'请选择图片...',buttonText:'浏览'"
                   accept="image/gif,image/jpeg,image/png" required="required"/>
        </div>
        <div class="fitem">
            <label>标题:</label>
            <input name="stitle" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>内容:</label>
            <input style="height: 90px" name="scontent" class="easyui-textbox" data-options="multiline:true"
                   required="required">
        </div>
    </form>
</div>
<!--提交与取消按钮-->
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saves()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>
<!--修改的window-->
<div id="dlg2" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons2">
    <div class="ftitle">修改数据</div>
    <form id="fm2" method="post">
        <div class="fitem">
            <label>标题:</label>
            <input name="stitle" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>内容:</label>
            <input style="height: 90px" name="scontent" class="easyui-textbox" data-options="multiline:true"
                   required="required">
        </div>
    </form>
</div>
<!--提交与取消按钮-->
<div id="dlg-buttons2">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveb2()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg2').dialog('close')">取消</a>
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

    //提交
    function submits() {
        //得到所有选中的数据
        var checkedItems = $('#dg').datagrid('getChecked');
        //定义一个数组，用来存取数据
        var ids = [];
        $.each(checkedItems, function (index, item) {
            ids.push({name: 'sids', value: item.sid});
        });
        $.post('<%=basePath %>serviceiaction/selectInformation.action', ids, function (result) {
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
    function news() {
        $('#dlg').dialog('open').dialog('setTitle', '新数据');
        //清空表单，来显示空表单
        $('#fm').form('clear');
        //提交数据处理的URL
        url = '<%=basePath %>serviceiaction/saveInformation.action';
    }

    //提交功能
    function saves() {
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

    //修改
    function edits() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg2').dialog('open').dialog('setTitle', '更新数据');
            //加载点击那一行的数据
            $('#fm2').form('load', row);
            //提交数据处理的URL
            url = '<%=basePath %>serviceiaction/updateInformation.action?sid=' + row.sid;
        }
    }

    //提交功能
    function saveb2() {
        $('#fm2').form('submit', {
            url: url,
            onSubmit: function () {
                //验证数据是否必填项完整
                return $(this).form('validate');
            },
            success: function (result) {
                //返回的是json数据，这里解析出来
                var result = eval('(' + result + ')');
                if (result.success) {//如果返回成功信息
                    $('#dlg2').dialog('close');		// 关闭window
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
    //删除
    function removes() {
        //得到那一行的数据，如果没选为空，不能进入if语句里面
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('警告', '是否确定删除本行数据?', function (r) {
                //如果选择确定，执行if里面语句
                if (r) {
                    //post提交
                    $.post('<%=basePath %>serviceiaction/removeInformation.action', {sid: row.sid}, function (result) {
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
