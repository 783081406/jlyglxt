<%--
  Created by IntelliJ IDEA.
  敬老院管理系统后台登录界面
  User: 陈彩君
  Date: 2017/9/28
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <title>敬老院管理系统</title>
    <meta charset="utf-8">
    <meta name="description" content="敬老院管理系统后台登录界面">
    <meta name="author" content="陈彩君">
    <!-- CSS -->
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/supersized.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body oncontextmenu="return false">
<div class="page-container">
    <h1>C<sup>c</sup>敬老院管理系统</h1>
    <form action="" method="post" id="myform">
        <div>
            <input type="text" id="userName" name="userName" class="username" placeholder="Username"
                   autocomplete="off"/>
        </div>
        <div>
            <input type="password" id="password" name="password" class="password" placeholder="Password"
                   oncontextmenu="return false"
                   onpaste="return false"/>
        </div>
        <button id="submit" type="button">登 录</button>
    </form>
    <div class="connect">
        <p>If we can only encounter each other rather than stay with each other,then I wish we had never
            encountered.</p>
        <p style="margin-top:20px;">如果只是遇见，不能停留，不如不遇见。</p>
    </div>
</div>
<div class="alert" style="display:none">
    <h2>消息</h2>
    <div class="alert_con">
        <p id="ts"></p>
        <p style="line-height:70px"><a class="btn">确定</a></p>
    </div>
</div>

<!-- Javascript -->
<script src="../jquery/jquery.min.js" type="text/javascript"></script>
<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<script>
    $(".btn").click(function () {
        is_hide();
    });
    var u = $("input[name=username]");
    var p = $("input[name=password]");
    $("#submit").live('click', function () {
        if (u.val() == '' || p.val() == '') {
            $("#ts").html("用户名或密码不能为空~");
            is_show();
            return false;
        } else {
            var reg = /^[0-9A-Za-z]+$/;
            if (!reg.exec(u.val())) {
                $("#ts").html("用户名错误");
                is_show();
                return false;
            }
        }
        //////ajax////////
        $.ajax({
            type: "post",
            url: "<%=basePath %>backstage/index.action",
            data:$('#myform').serialize(),
            dataType: "json",
            success: function (data) {
                $("#ts").html(data.message);
                is_show();
                return false;
            }
        });
    });

    window.onload = function () {
        $(".connect p").eq(0).animate({"left": "0%"}, 600);
        $(".connect p").eq(1).animate({"left": "0%"}, 400);
    };
    function is_hide() {
        $(".alert").animate({"top": "-40%"}, 300)
    }
    function is_show() {
        $(".alert").show().animate({"top": "45%"}, 300)
    }
</script>
</body>
</html>

