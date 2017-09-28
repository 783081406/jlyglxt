<%--
  Created by IntelliJ IDEA.
  敬老院管理系统后台首页
  User: 陈彩君
  Date: 2017/9/28
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>敬老院管理系统</title>
    <meta charset="utf-8">
    <meta name="description" content="敬老院管理系统后台首页">
    <meta name="author" content="陈彩君">
    <!--EasyUI相关的文件的引入-->
    <link rel="stylesheet" href="../../easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" href="../../easyui/themes/icon.css"/>
    <script type="text/javascript" src="../../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../easyui/locale/easyui-lang-zh_CN.js"></script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<!--header-->
<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
    <span style="float:right; padding-right:20px;" class="head">欢迎 管理员
        <a href="#" id="editpass">修改密码</a>
        <a href="#" id="loginOut">安全退出</a></span>
    <span style="padding-left:10px; font-size: 16px; ">项目管理系统</span>
</div>
<!--导航菜单-->
<div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
    <div id="nav" class="easyui-accordion" fit="true" border="false">
        <!--  导航内容 -->
    </div>
</div>
<!--主要内容tab-->
<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; ">
            <h1 style="font-size:24px;">欢迎使用C<sup>c</sup>敬老院管理系统</h1>
        </div>
    </div>
</div>
<!--footer-->
<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
    <div class="footer">版权归属于C<sup>c</sup>敬老院，翻版必究</div>
</div>
</body>
</html>
