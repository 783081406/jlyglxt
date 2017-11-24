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
<div style="float:left; width:260px; height:300px; margin-right:50px">
    <div id="p2" class="easyui-panel" title="伙食费" style="width:260px; height:300px;padding:10px;"
         data-options="iconCls:'icon-save',closable:true,tools:'#tt2'">
        <div class="ftitle">伙食费用</div>
        <form id="fm2" method="post" novalidate>
            <div class="fitem">
                <label>最低:</label>
                <input name="rtype1" class="easyui-validatebox">
            </div>
            <br/><br/>
            <div class="fitem">
                <label>最高:</label>
                <input name="rtype2" class="easyui-validatebox">
            </div>
        </form>
    </div>
    <div id="tt2">
        <a href="javascript:void(0)" class="icon-edit" onclick="edit2()"></a>
    </div>
</div>
<div style="float:left; width:260px; height:300px; margin-right:50px">
    <div id="p3" class="easyui-panel" title="护理费" style="width:260px; height:300px;padding:10px;"
         data-options="iconCls:'icon-save',closable:true,tools:'#tt3'">
        <div class="ftitle">护理费用</div>
        <form id="fm3" method="post" novalidate>
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
                <input name="rtype2" class="easyui-validatebox">
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
            <input name="rtype2" class="easyui-validatebox">
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
            <input name="rtype1" class="easyui-validatebox">
        </div>
        <br/><br/>
        <div class="fitem">
            <label>最高:</label>
            <input name="rtype2" class="easyui-validatebox">
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
            <input name="rtype2" class="easyui-validatebox">
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
    var url;
    //入住费用
    function edit1() {

    }
    //提交
    function save1() {

    }

    //伙食费
    function edit2() {

    }
    //提交
    function save2() {

    }

    //护理费
    function edit3() {

    }
    //提交
    function save3() {

    }

</script>
</body>
</html>
