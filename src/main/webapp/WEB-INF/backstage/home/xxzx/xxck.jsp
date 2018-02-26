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
    <link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>backstage/home/css/mycss.css"/><!--自定义css-->
    <script type="text/javascript" src="<%=basePath %>easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<table id="dg" title="信息列表" class="easyui-datagrid" style="width:1200px;height:400px"
       url="<%=basePath %>elderlyimaction/getAllInformation.action"
       toolbar="#toolbar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="ename" width="100">名字</th>
        <th field="sex" width="50">性别</th>
        <th field="idcard" width="200">身份证号</th>
        <th field="phone" width="150">手机号</th>
        <th field="birthDate" width="150">出生日期</th>
        <th field="homeAddress" width="400">家庭住址</th>
        <th field="originAddress" width="400">户籍住址</th>
    </tr>
    </thead>
</table>
<!--大table的工具栏-->
<div id="toolbar">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-more" plain="true" onclick="morec()">查看</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newc()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editc()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removec()">删除</a>
    </div>
    <div>
        <div style="padding:0 0 0 7px;color:#333;">
            查询姓名：<input type="text" class="textbox" name="ename" style="width:150px;height:25px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search();">查询</a>
        </div>
    </div>
</div>
<!--////////////////////////////////////////////////-->
<!--查看的window-->
<div id="morecheck" class="easyui-dialog" style="width:680px;height:270px;padding:10px 20px" closed="true">
    <div class="ftitle">家庭信息</div>
    <div>
        <table id="moret" class="easyui-datagrid" style="width: 620px;height: auto;" title="家庭信息" toolbar="#mtoolbar"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
            <tr>
                <th field="familyName" width="50">家属名字</th>
                <th field="relationship" width="50">关系</th>
                <th field="faddress" width="200">家庭地址</th>
                <th field="phone" width="80">联系电话</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<!--查看window的工具栏-->
<div id="mtoolbar">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newm()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editm()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removem()">删除</a>
    </div>
</div>
<!--////////////////////////////////////////////////-->
<!--新账户window-->
<div id="dlg" class="easyui-dialog" style="width:400px;height:340px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="ftitle">相关信息</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>名字:</label>
            <input name="ename" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>性别:</label>
            <select name="sex" class="easyui-combobox" style="width:173px;" required="required">
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
        <div class="fitem">
            <label>身份证号:</label>
            <input name="idcard" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>手机号:</label>
            <input name="phone" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>出生日期:</label>
            <input name="birthDate" class="easyui-datebox" required="required">
        </div>
        <div class="fitem">
            <label>家庭住址:</label>
            <input name="homeAddress" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>户籍住址:</label>
            <input name="originAddress" class="easyui-validatebox" required="required">
        </div>
    </form>
</div>
<!--/////////////////////////////////////////////////////-->
<!--提交与取消按钮-->
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>

<!--//////////////////////////////////////////////////////-->
<!--家庭信息的添加window-->
<div id="mdlg" class="easyui-dialog" style="width:380px;height:280px;padding:10px 20px" closed="true"
     buttons="#mdlg-buttons">
    <div class="ftitle">家属信息</div>
    <form id="mfm" method="post" novalidate>
        <div class="fitem">
            <label>家属名字:</label>
            <input name="familyName" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>关系:</label>
            <input name="relationship" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>家庭地址:</label>
            <input name="faddress" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>联系电话:</label>
            <input name="phone" class="easyui-validatebox" required="required">
        </div>
    </form>
</div>
<!--提交与取消按钮-->
<div id="mdlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savem()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#mdlg').dialog('close')">取消</a>
</div>

<!--////////////////////////////////////////////////////////////////////////-->
<!--自定义javascript-->
<script type="text/javascript">
    var url;
    var eiId;
    //搜索功能
    obj = {
        search: function () {
            $('#dg').datagrid('load', {
                ename: $.trim($('input[name="ename"]').val())
            });
        }
    };
    //增加家属信息功能
    /*    function moreadd() {
     $('#te').append("<tr>" +
     "<td><input name='familyName' class='easyui-validatebox' required='required'/></td>" +
     "<td><input name='relationship' class='easyui-validatebox' required='required'/></td>" +
     "<td><input name='faddress' class='easyui-validatebox' required='required'/></td>" +
     "<td><input name='phone' class='easyui-validatebox' required='required'/></td>" +
     "</tr>");
     }*/
    //////////////////////////////////////////////////////////////////////////
    //查看功能
    function morec() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#morecheck').dialog('open').dialog('setTitle', '详细信息');
            //加载table数据
            $('#moret').datagrid('load', '<%=basePath %>familyimaction/getAllInformation.action?eiId=' + row.eiId);
            eiId = row.eiId;
        }
    }
    ////////////////////////////////////////////////////////////////////////
    //添加功能(大表格)
    function newc() {
        $('#dlg').dialog('open').dialog('setTitle', '相关信息');
        //清空表单，来显示空表单
        $('#fm').form('clear');
        //提交数据处理的URL
        url = '<%=basePath %>elderlyimaction/saveInformation.action';
    }

    //添加功能（细表格）
    function newm() {
        $('#mdlg').dialog('open').dialog('setTitle', '添加信息');
        //清空表单，来显示空表单
        $('#mfm').form('clear');
        //提交数据处理的URL
        url = '<%=basePath %>familyimaction/saveInformation.action?eiId=' + eiId;
    }
    /////////////////////////////////////////////////////////////////////////
    //修改功能（大表格）
    function editc() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlg').dialog('open').dialog('setTitle', '更新信息');
            //加载点击那一行的数据
            $('#fm').form('load', row);
            //提交数据处理的URL
            url = '<%=basePath %>elderlyimaction/updateInformation.action?eiId=' + row.eiId;
        }
    }

    //修改功能（细表格）
    function editm() {
        var row = $('#moret').datagrid('getSelected');
        if (row) {
            $('#mdlg').dialog('open').dialog('setTitle', '更新信息');
            //加载点击那一行的数据
            $('#mfm').form('load', row);
            //提交数据处理的URL
            url = '<%=basePath %>familyimaction/updateInformation.action?fId=' + row.fId;
        }
    }
    /////////////////////////////////////////////////////////////////////////
    //提交功能(大表格)
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
    //提交功能(小表格)
    function savem() {
        $('#mfm').form('submit', {
            url: url,
            onSubmit: function () {
                //验证数据是否必填项完整
                return $(this).form('validate');
            },
            success: function (result) {
                //返回的是json数据，这里解析出来
                var result = eval('(' + result + ')');
                if (result.success) {//如果返回成功信息
                    $('#mdlg').dialog('close');		// 关闭window
                    $('#moret').datagrid('reload');	//重新加载数据
                } else {//返回是失败信息
                    $.messager.show({//弹出提示框来说明插入失败以及返回的信息
                        title: '错误提示',
                        msg: result.msg
                    });
                }
            }
        });
    }
    ///////////////////////////////////////////////////////////////////////////////
    //删除功能（大表格）
    function removec() {
        //得到那一行的数据，如果没选为空，不能进入if语句里面
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('警告', '是否确定删除本行数据?', function (r) {
                //如果选择确定，执行if里面语句
                if (r) {
                    //post提交
                    $.post('<%=basePath %>elderlyimaction/removeInformation.action', {eiId: row.eiId}, function (result) {
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
    //删除功能（小表格）
    function removem() {
        //得到那一行的数据，如果没选为空，不能进入if语句里面
        var row = $('#moret').datagrid('getSelected');
        if (row) {
            $.messager.confirm('警告', '是否确定删除本行数据?', function (r) {
                //如果选择确定，执行if里面语句
                if (r) {
                    //post提交
                    $.post('<%=basePath %>familyimaction/removeInformation.action', {fId: row.fId}, function (result) {
                        if (result.success) {
                            $('#moret').datagrid('reload');	// 重新加载数据
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
