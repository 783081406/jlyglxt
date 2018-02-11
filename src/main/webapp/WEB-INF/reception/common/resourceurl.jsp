<%--
  Created by IntelliJ IDEA.
  User: ccjjltx
  Date: 2018/2/11
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="<%=basePath %>bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=basePath %>reception/css/img.css"/>
<script type="text/javascript" src="<%=basePath %>jquery/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="<%=basePath %>bootstrap/js/bootstrap.min.js"></script>
<style>
    .box1 {
        position: relative;
        display: block;
        /*border: 1px solid orange;*/
        width: 750px;
        height: 50px;
        left: 50%;
        transform: translateX(-50%);
        margin-top: 60px;
        margin-bottom: 60px;
    }

    .left1,
    .right1,
    .content1 {
        position: absolute;
        display: inline-block;
        top: 50%;
        transform: translateY(-50%); /*垂直居中*/
    }

    .left1,
    .right1 {
        border: 1px solid gainsboro; /*改变左右短线的颜色*/
    }

    .left1 {
        left: 1%;
        width: 31%;
        margin-right: 3%;
    }

    .content1 {
        height: 20px;
        line-height: 20px;
        text-align: center;
        font-family: "微软雅黑";
        left: 35%;
        width: 30%;
    }

    .right1 {
        left: 65%;
        width: 31%;
        margin-left: 3%;
    }
</style>
