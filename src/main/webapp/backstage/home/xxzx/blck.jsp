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
<!--病历》tool-->
<div id="toolbl">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-more" plain="true" onclick="morebl()">更多</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-more" plain="true" onclick="xdbl()">心电信息</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-more" plain="true" onclick="jybl()">就医记录</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newbl()">添加</a>
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
<div id="bld" class="easyui-dialog" style="width:400px;height:420px;padding:10px 20px" closed="true"
     buttons="#blt-buttons">
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
            <input name="physician" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>医院:</label>
            <input name="hospital" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>医院电话:</label>
            <input name="hospitalPhone" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>过敏药物:</label>
            <input name="allergyDrugs" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>主要疾病:</label>
            <input name="majorDiseases" class="easyui-validatebox">
        </div>
        <div class="ftitle">血压血氧</div>
        <div class="fitem">
            <label>高压:</label>
            <input name="highHanded" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>低压:</label>
            <input name="lowHanded" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>血氧值:</label>
            <input name="bloodOxygenValue" class="easyui-validatebox">
        </div>
        <div class="ftitle">血糖信息</div>
        <div class="fitem">
            <label>空腹血糖:</label>
            <input name="fastingPlasmaGlucose" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>餐后血糖:</label>
            <input name="postprandialBoolGlucose" class="easyui-validatebox">
        </div>
        <div class="ftitle">血脂信息</div>
        <div class="fitem">
            <label>总胆固醇:</label>
            <input name="totalCholesterol" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>甘油三酯:</label>
            <input name="triglyceride" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>高密度脂蛋白胆固醇:</label>
            <input name="hdlc" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>低密度脂蛋白胆固醇:</label>
            <input name="ldlc" class="easyui-validatebox">
        </div>
    </form>
</div>
<!--病历》提交与取消按钮-->
<div id="blt-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editbl()">修改</a>
    <a id="savebl" href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savebl()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#bld').dialog('close')">取消</a>
</div>


<!--//////////////////////////////////////////////////////////////////////////////////////-->
<!--心电信息》table-->
<div id="xdtd" class="easyui-dialog" style="width:680px;height:270px;padding:10px 20px" closed="true">
    <div class="ftitle">心电信息</div>
    <div>
        <table id="xdt" class="easyui-datagrid" style="width: 620px;height: auto;" title="心电信息" toolbar="#toolxd"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
            <tr>
                <th field="qrs" width="100">qrs区间</th>
                <th field="comment" width="100">全科医生批注</th>
                <th field="rr" width="100">rr期间</th>
                <th field="analysisResult" width="100">医生分析结果</th>
                <th field="rhythm" width="100">心率</th>
                <th field="rdate" width="200">诊断时间</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<!--心电信息》tool-->
<div id="toolxd">
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newxd()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editxd()">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removexd()">删除</a>
    </div>
</div>
<!--心电信息》form-->
<div id="xdd" class="easyui-dialog" style="width:400px;height:340px;padding:10px 20px" closed="true"
     buttons="#xdt-buttons">
    <div class="ftitle">心电信息</div>
    <form id="xdf" method="post" novalidate>
        <div class="fitem">
            <label>qrs区间:</label>
            <input name="qrs" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>全科医生批注:</label>
            <input name="comment" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>rr期间:</label>
            <input name="rr" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>医生分析结果:</label>
            <input name="analysisResult" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>心率:</label>
            <input name="rhythm" class="easyui-validatebox" required="required">
        </div>
        <div class="fitem">
            <label>诊断时间:</label>
            <input name="rdate" class="easyui-datebox" required="required">
        </div>
    </form>
</div>
<!--心电信息》提交与取消按钮-->
<div id="xdt-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savexd()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#xdd').dialog('close')">取消</a>
</div>
<!--//////////////////////////////////////////////////////////////////////////////////////-->
<!--自定义的CSS-->
<script>
    ///////////////变量////////////////////
    var chId;
    var url;
    ////////////////////////////////////////////////////////////////////
    //搜索功能
    obj = {
        search: function () {
            $('#blt').datagrid('load', {
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
        //得到那行的数据
        var row = $('#blt').datagrid('getSelected');
        if (row) {
            $("#bld").dialog('open').dialog('setTitle', '病历详细');
            //设置不可编辑
            $("#blf div input").attr("disabled", "disabled");
            //加载数据
            $("#blf").form('load', '<%=basePath %>chaction/getAllInformation2.action?chId=' + row.chId);
            $("#box").combobox({disabled: true});
            //禁止save按钮
            $("#savebl").linkbutton('disable');
            chId = row.chId;
        }
    }

    //心电信息
    function xdbl() {
        var row = $('#blt').datagrid('getSelected');
        if (row) {
            $("#xdtd").dialog('open').dialog('setTitle', '相关心电信息');
            //加载
            $('#xdt').datagrid('load', '<%=basePath %>ecgiaction/getAllInformation.action?chId=' + row.chId);
            chId = row.chId;
        }
    }

    //就医记录
    function jybl() {

    }

    //增加
    function newbl() {

    }

    //删除
    function removebl() {

    }

    //修改
    function editbl() {
        //设置可编辑
        $("#blf div input").attr("disabled", false);
        //开启save按钮
        $("#savebl").linkbutton('enable');
        //提交的url
        url = '<%=basePath %>chaction/updateInformation.action?chId=' + chId;
    }

    //保存
    function savebl() {
        $('#blf').form('submit', {
            url: url,
            onSubmit: function () {
                //验证数据是否必填项完整
                return $(this).form('validate');
            },
            success: function (result) {
                //返回的是json数据，这里解析出来
                var result = eval('(' + result + ')');
                if (result.success) {//如果返回成功信息
                    $('#bld').dialog('close');		// 关闭window
                    $('#blt').datagrid('reload');	//重新加载数据
                } else {//返回是失败信息
                    $.messager.show({//弹出提示框来说明插入失败以及返回的信息
                        title: '错误提示',
                        msg: result.msg
                    });
                }
            }
        });
    }
    ////////////////////////////心电信息////////////////////////////////////////
    //添加功能
    function newxd() {
        $("#xdd").dialog('open').dialog('setTitle', '增加心电信息');
        //清空表单，来显示空表单
        $("#xdf").form('clear');
        //提交数据处理的url
        url = '<%=basePath %>ecgiaction/saveInformation.action?chId=' + chId;
    }

    //修改
    function editxd() {
        var row = $('#xdt').datagrid('getSelected');
        if (row) {
            $('#xdd').dialog('open').dialog('setTitle', '更新心电信息');
            //加载点击那一行的数据
            $('#xdf').form('load', row);
            //提交数据处理的URL
            url = '<%=basePath %>ecgiaction/updateInformation.action?ecgId=' + row.ecgId;
        }
    }

    //提交
    function savexd() {
        $('#xdf').form('submit', {
            url: url,
            onSubmit: function () {
                //验证数据是否必填项完整
                return $(this).form('validate');
            },
            success: function (result) {
                //返回的是json数据，这里解析出来
                var result = eval('(' + result + ')');
                if (result.success) {//如果返回成功信息
                    $('#xdd').dialog('close');		// 关闭window
                    $('#xdt').datagrid('reload');	//重新加载数据
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
    function removexd() {

    }

    /////////////////////////////就医记录///////////////////////////////////////
</script>
</body>
</html>
