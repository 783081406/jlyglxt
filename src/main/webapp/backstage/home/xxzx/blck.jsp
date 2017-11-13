<%--
  Created by IntelliJ IDEA.
  病历查看
  User: ccjjltx
  Date: 2017/10/16
  Time: 23:56
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
    <title>病历查看</title>
    <meta charset="utf-8"/>
    <meta name="description" content="员工中心->员工信息"/>
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
<!--病历》table-->
<table id="blt" title="病历" class="easyui-datagrid" style="width:980px;height:400px"
       url="<%=basePath %>chaction/getAllInformation.action"
       toolbar="#toolbl" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="ename" width="80">姓名</th>
        <th field="height" width="60">身高(m)</th>
        <th field="weight" width="60">体重(m)</th>
        <th field="physician" width="80">主治医师</th>
        <th field="hospital" width="160">医院</th>
        <th field="hospitalPhone" width="120">医院电话</th>
        <th field="allergyDrugs" width="150">过敏药物</th>
        <th field="majorDiseases" width="150">主要疾病</th>
    </tr>
    </thead>
</table>
<div id="toolbl">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-more" plain="true" onclick="morebl()">更多</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newbl()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editbl()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removebl()">删除</a>
    </div>
    <div>
        <div style="padding:0 0 0 7px;color:#333;">
            查询姓名：<input type="text" class="textbox" name="ename" style="width:150px;height:25px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search();">查询</a>
        </div>
    </div>
</div>
<!--病历》表单-->
<div id="bld" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="blf" method="post" novalidate>
        <div class="ftitle">基本特征</div>
        <div class="fitem">
            <label>姓名:</label>
            <input id="box" name="ename" required="required" style="width:173px;">
        </div>
        <div class="fitem">
            <label>身高:</label>
            <input name="height" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>体重:</label>
            <input name="weight" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>主治医师:</label>
            <input name="physician" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>医院:</label>
            <input name="hospital" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>医院电话:</label>
            <input name="hospitalName" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>过敏药物:</label>
            <input name="allergyDrugs" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>主要疾病:</label>
            <input name="majorDiseases" class="easyui-validatebox" required="required">
        </div>
        <div class="ftitle">血压血氧</div>
        <div class="fitem">
            <label>高压:</label>
            <input name="highHanded" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>低压:</label>
            <input name="lowHanded" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>血氧值:</label>
            <input name="bloodOxygenValue" class="easyui-validatebox" required="required">
        </div>
        <div class="ftitle">血糖信息</div>
        <div class="fitem">
            <label>空腹血糖:</label>
            <input name="fastingPlasmaGlucose" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>餐后血糖:</label>
            <input name="postprandialBoolGlucose" class="easyui-validatebox" required="required">
        </div>
        <div class="ftitle">血脂信息</div>
        <div class="fitem">
            <label>总胆固醇:</label>
            <input name="totalCholesterol" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>甘油三酯:</label>
            <input name="triglyceride" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>高密度脂蛋白胆固醇:</label>
            <input name="hdlc" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>低密度脂蛋白胆固醇:</label>
            <input name="ldlc" class="easyui-validatebox" required="required">
        </div>
    </form>
</div>
<!--病历》提交与取消按钮-->
<div id="blt-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savebl()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#bld').dialog('close')">取消</a>
</div>

<!--自定义的CSS-->
<script>
    ////////////////////////////////////////////////////////////////////
    //搜索功能
    obj = {
        search: function () {
            $('#dg').datagrid('load', {
                ename: $.trim($('input[name="ename"]').val())
            });
        }
    };
    //下拉框功能
    $(function () {
        $("#box").combobox({
            valueField: 'eId',
            textField: 'ename',
            url: '<%=basePath %>elderaction/getIdElderName.action'
        })
    });
    /////////////////////////////病历////////////////////////////////////
    //更多
    function morebl() {

    }

    //增加
    function newbl() {

    }

    //编辑
    function editbl() {

    }

    //删除
    function removebl() {

    }

    //保存
    function savebl() {

    }
    ////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////
</script>
</body>
</html>
