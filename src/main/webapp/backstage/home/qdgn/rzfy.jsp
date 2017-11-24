<%--
  Created by IntelliJ IDEA.
  入住费用
  User: ccjjltx
  Date: 2017/10/16
  Time: 23:52
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
    <title>入住费用</title>
    <meta charset="utf-8"/>
    <meta name="description" content="前端功能->入住费用"/>
    <meta name="author" content="陈彩君"/>
    <link rel="stylesheet" type="text/css" href="../../../easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="../../../easyui/themes/default/easyui.css"/>
    <!--自定义css-->
    <link rel="stylesheet" type="text/css" href="../css/mycss.css"/>
    <script type="text/javascript" src="../../../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../../../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../../easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body style="padding-top: 50px;padding-left: 50px;">
<div style="float:left; width:260px; height:300px; margin-right:50px">
    <div id="p1" class="easyui-panel" title="入住费用" style="width:260px; height:300px;padding:10px;"
         data-options="iconCls:'icon-save',closable:true,tools:'#tt1'">
        <div class="ftitle">入住费用</div>
        <form id="fm1" method="post" novalidate>
            <div class="fitem">
                <label>标准:</label>
                <input name="rtype1" class="easyui-validatebox">
            </div>
            <br/><br/>
            <div class="fitem">
                <label>中等:</label>
                <input name="rtype2" class="easyui-validatebox">
            </div>
            <br/><br/>
            <div class="fitem">
                <label>豪华:</label>
                <input name="rtype3" class="easyui-validatebox">
            </div>
        </form>
    </div>
    <div id="tt1">
        <a href="javascript:void(0)" class="icon-edit" onclick="edit1()"></a>
    </div>
</div>
<!--伙食费-->
<div style="float:left; width:260px; height:300px; margin-right:50px">
    <div id="p2" class="easyui-panel" title="伙食费" style="width:260px; height:300px;padding:10px;"
         data-options="iconCls:'icon-save',closable:true,tools:'#tt2'">
        <div class="ftitle">伙食费用</div>
        <form id="fm2" method="post" novalidate>
            <div class="fitem">
                <label>最低:</label>
                <input name="minimum" class="easyui-validatebox">
            </div>
            <br/><br/>
            <div class="fitem">
                <label>最高:</label>
                <input name="highest" class="easyui-validatebox">
            </div>
        </form>
    </div>
    <div id="tt2">
        <a href="javascript:void(0)" class="icon-edit" onclick="edit2()"></a>
    </div>
</div>
<!--护理费-->
<div style="float:left; width:260px; height:300px; margin-right:50px">
    <div id="p3" class="easyui-panel" title="护理费" style="width:260px; height:300px;padding:10px;"
         data-options="iconCls:'icon-save',closable:true,tools:'#tt3'">
        <div class="ftitle">护理费用</div>
        <form id="fm3" method="post" novalidate>
            <div class="fitem">
                <label>初级护理:</label>
                <input name="ncost1" class="easyui-validatebox">
            </div>
            <br/><br/>
            <div class="fitem">
                <label>中级护理:</label>
                <input name="ncost2" class="easyui-validatebox">
            </div>
            <br/><br/>
            <div class="fitem">
                <label>高级护理:</label>
                <input name="ncost3" class="easyui-validatebox">
            </div>
        </form>
    </div>
    <div id="tt3">
        <a href="javascript:void(0)" class="icon-edit" onclick="edit3()"></a>
    </div>
</div>

<!--三个修改表单-->
<!--入住费用-->
<div id="dlg1" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons1">
    <div class="ftitle">修改信息</div>
    <form id="fm11" method="post" novalidate>
        <div class="fitem">
            <label>初级:</label>
            <input name="rtype1" class="easyui-validatebox">
        </div>
        <br/><br/>
        <div class="fitem">
            <label>中级:</label>
            <input name="rtype2" class="easyui-validatebox">
        </div>
        <br/><br/>
        <div class="fitem">
            <label>高级:</label>
            <input name="rtype3" class="easyui-validatebox">
        </div>
    </form>
</div>
<div id="dlg-buttons1">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save1()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg1').dialog('close')">取消</a>
</div>
<!--伙食费-->
<div id="dlg2" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons2">
    <div class="ftitle">修改信息</div>
    <form id="fm21" method="post" novalidate>
        <div class="fitem">
            <label>最低:</label>
            <input name="minimum" class="easyui-validatebox">
        </div>
        <br/><br/>
        <div class="fitem">
            <label>最高:</label>
            <input name="highest" class="easyui-validatebox">
        </div>
    </form>
</div>
<div id="dlg-buttons2">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save2()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg2').dialog('close')">取消</a>
</div>
<!--护理费-->
<div id="dlg3" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px" closed="true"
     buttons="#dlg-buttons3">
    <div class="ftitle">修改信息</div>
    <form id="fm31" method="post" novalidate>
        <div class="fitem">
            <label>初级护理:</label>
            <input name="ncost1" class="easyui-validatebox">
        </div>
        <br/><br/>
        <div class="fitem">
            <label>中级护理:</label>
            <input name="ncost2" class="easyui-validatebox">
        </div>
        <br/><br/>
        <div class="fitem">
            <label>高级护理:</label>
            <input name="ncost3" class="easyui-validatebox">
        </div>
    </form>
</div>
<div id="dlg-buttons3">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save2()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg3').dialog('close')">取消</a>
</div>
<!--javascript-->
<script>
    //加载完html之后自动加载的数据javascript
    $(function () {
        //入住费用的加载数据
        $("#fm1").form('load', '<%=basePath %>roomcostaction/getAllInformation.action');
        $("#fm1 div input").attr("disabled", "disabled");
        //伙食费的加载数据
        $("#fm2").form('load', '<%=basePath %>boardwagesaction/getAllInformation.action');
        $("#fm2 div input").attr("disabled", "disabled");
        //伙食费的加载数据
        $("#fm3").form('load', '<%=basePath %>nursingfeeaction/getAllInformation.action');
        $("#fm3 div input").attr("disabled", "disabled");
    });
    var url;
    //入住费用
    function edit1() {
        //open div1
        $("#dlg1").dialog('open').dialog('setTitle', '入住费用');
        //清空表单，来显示空表单
        $("#fm11").form('load', '<%=basePath %>roomcostaction/getAllInformation.action');
        url = '<%=basePath %>roomcostaction/updateInformation.action';
    }
    //提交
    function save1() {
        $('#fm11').form('submit', {
            url: url,
            onSubmit: function () {
                //验证数据是否必填项完整
                return $(this).form('validate');
            },
            success: function (result) {
                //返回的是json数据，这里解析出来
                var result = eval('(' + result + ')');
                if (result.success) {//如果返回成功信息
                    $('#dlg1').dialog('close');		// 关闭window
                    //清除数据
                    $('#fm1').form('clear');
                    //重新加载
                    $("#fm1").form('load', '<%=basePath %>roomcostaction/getAllInformation.action');
                } else {//返回是失败信息
                    $.messager.show({//弹出提示框来说明插入失败以及返回的信息
                        title: '错误提示',
                        msg: result.msg
                    });
                }
            }
        });
    }

    //伙食费
    function edit2() {
        //open div1
        $("#dlg2").dialog('open').dialog('setTitle', '伙食费');
        //清空表单，来显示空表单
        $("#fm21").form('load', '<%=basePath %>boardwagesaction/getAllInformation.action');
        url = '<%=basePath %>boardwagesaction/updateInformation.action';
    }
    //提交
    function save2() {
        $('#fm21').form('submit', {
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
                    //清除数据
                    $('#fm2').form('clear');
                    //重新加载
                    $("#fm2").form('load', '<%=basePath %>boardwagesaction/getAllInformation.action');
                } else {//返回是失败信息
                    $.messager.show({//弹出提示框来说明插入失败以及返回的信息
                        title: '错误提示',
                        msg: result.msg
                    });
                }
            }
        });
    }

    //护理费
    function edit3() {
        //open div1
        $("#dlg3").dialog('open').dialog('setTitle', '护理费');
        //清空表单，来显示空表单
        $("#fm31").form('load', '<%=basePath %>nursingfeeaction/getAllInformation.action');
        url = '<%=basePath %>nursingfeeaction/updateInformation.action';
    }
    //提交
    function save3() {
        $('#fm31').form('submit', {
            url: url,
            onSubmit: function () {
                //验证数据是否必填项完整
                return $(this).form('validate');
            },
            success: function (result) {
                //返回的是json数据，这里解析出来
                var result = eval('(' + result + ')');
                if (result.success) {//如果返回成功信息
                    $('#dlg3').dialog('close');		// 关闭window
                    //清除数据
                    $('#fm3').form('clear');
                    //重新加载
                    $("#fm3").form('load', '<%=basePath %>nursingfeeaction/getAllInformation.action');
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
