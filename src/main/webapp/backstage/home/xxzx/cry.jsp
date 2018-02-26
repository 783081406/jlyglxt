<%--
  Created by IntelliJ IDEA.
  出入院
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
    <title>出入院</title>
    <meta charset="utf-8"/>
    <meta name="description" content="信息中心->出入院"/>
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
<table id="dg" title="出入院列表" class="easyui-datagrid" style="width:800px;height:400px"
       url="<%=basePath %>eaeaction/getAllInformation.action"
       toolbar="#toolbar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="ename" width="100">姓名</th>
        <th field="stime" width="150">进入时间</th>
        <th field="etime" width="150">离开时间</th>
        <th field="isIn" width="100">是否离开</th>
    </tr>
    </thead>
</table>
<!--工具栏-->
<div id="toolbar">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newe()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edite()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removee()">删除</a>
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
    <div class="ftitle">信息</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>姓名:</label>
            <input id="box" name="ename" style="width:173px;">
        </div>
        <div class="fitem">
            <label>进入时间:</label>
            <input name="stime" class="easyui-datebox" required="required">
        </div>
        <div class="fitem">
            <label>离开时间:</label>
            <input name="etime" class="easyui-datebox">
        </div>
        <div class="fitem">
            <label>是否离开:</label>
            <select name="isIn" class="easyui-combobox" style="width:173px;" required="required">
                <option value="1">在住</option>
                <option value="2">离院</option>
            </select>
        </div>
    </form>
</div>
<!--提交与取消按钮-->
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savee()">保存</a>
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
    function newe() {
        $('#dlg').dialog('open').dialog('setTitle', '新信息');
        //清空表单，来显示空表单
        $('#fm').form('clear');
        //提交数据处理的URL
        url = '<%=basePath %>eaeaction/addInformation.action';
    }

    //修改
    function edite() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', '修改信息');
            //加载点击那一行的数据
            $('#fm').form('load', row);
            //提交数据处理的URL
            url = '<%=basePath %>eaeaction/updateInformation.action?eaeid=' + row.eaeid;
        }
    }

    //提交功能
    function savee() {
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
    function removee() {
        //得到那一行的数据，如果没选为空，不能进入if语句里面
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('警告', '是否确定删除本行数据?', function (r) {
                //如果选择确定，执行if里面语句
                if (r) {
                    //post提交
                    $.post('<%=basePath %>eaeaction/removeInformation.action', {eaeid: row.eaeid}, function (result) {
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
