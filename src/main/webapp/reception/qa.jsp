<%--
  Created by IntelliJ IDEA.
  前端：常见问题
  User: ccjjltx
  Date: 2017/11/23
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>常见问题</title>
    <meta charset="utf-8"/>
    <meta name="description" content="前端->常见问题"/>
    <meta name="author" content="陈彩君"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="./css/img.css"/>
    <script type="text/javascript" src="../jquery/jquery-3.0.0.min.js"></script>
    <script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
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
</head>
<body>
<!--头文件-->
<div class="header">
    <div style="float: left;padding-left: 40px"><h2><strong>C<sup>c</sup>敬老院养老机构</strong></h2></div>
    <div style="float: right;padding-right: 40px">
        <h3>
            <small>全国电话：</small>
            <strong><em class="text-primary">020-12345678</em></strong></h3>
    </div>
    <div style="clear: both;"></div>
</div>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"></a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="#" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;首页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                    <small style="font-size: 11px">Home</small>
                </a></li>
                <li><a href="#" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生活环境&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                    <small style="font-size: 11px">Environment</small>
                </a></li>
                <li><a href="#" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新闻动态&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                    <small style="font-size: 11px">News</small>
                </a></li>
                <li><a href="#" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;入住费用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                    <small style="font-size: 11px">Cost</small>
                </a></li>
                <li class="active"><a href="#" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;常见问题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                    <small style="font-size: 11px">Q&A</small>
                </a></li>
                <li><a href="#" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系我们&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                    <small style="font-size: 11px">Contact Us</small>
                </a></li>
            </ul>
        </div>
        <div class="navbar-footer">
            <a class="navbar-brand" href="#"></a>
        </div>
    </div>
</nav>
<!--宣传栏-->
<div class="carousel slide" id="circleContent">
    <ol class="carousel-indicators">
        <li data-target="#circleContent" data-slide-to="0" class="active">
        </li>
        <c:forEach var="x" begin="1" end="${lbSize}">
            <li data-target="#circleContent" data-slide-to="${x}">
            </li>
        </c:forEach>

    </ol>
    <div class="carousel-inner">
        <s:iterator value="listBillboards" var="bb" status="bbb">
            <div class="item <s:if test="#bbb.first">
                active
            </s:if>">
                <img alt="" src='img/billboards/<s:property value="#bb.bpath"/>'/>
                <div class="carousel-caption">
                    <h4>
                        <s:property value="#bb.btitle"/>
                    </h4>
                    <p>
                        <s:property value="#bb.bcontent"/>
                    </p>
                </div>
            </div>
        </s:iterator>
    </div>
</div>
<!--内容-->
<div class="container" style="margin-top: 50px;">
    <div class="row">
        <div class="col-md-4"
             style="background-color: #91d945;padding-top: 50px;padding-right:22px;padding-bottom: 50px">
            <div class="row">
                <div class="col-md-12"><span style="color: #ffffff;font-size: 18px;">老年活动</span>
                    <hr style="height:5px;background-color: #ffffff"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6"><img src='./img/qa/a01.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">舒筋活络</span></p></div>
                <div class="col-md-6"><img src='./img/qa/a02.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">报刊阅读</span></p></div>
            </div>
            <div class="row">
                <div class="col-md-6"><img src='./img/qa/a03.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">一手好字</span></p></div>
                <div class="col-md-6"><img src='./img/qa/a04.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">早上空气清新</span></p></div>
            </div>
            <div class="row">
                <div class="col-md-6"><img src='./img/qa/a05.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">拉一曲二胡</span></p></div>
                <div class="col-md-6"><img src='./img/qa/a06.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">踢毽子灵活肢体</span></p></div>
            </div>
            <div class="row">
                <div class="col-md-6"><img src='./img/qa/a07.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">晨练中的老人</span></p></div>
                <div class="col-md-6"><img src='./img/qa/a08.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">练练手力</span></p></div>
            </div>
        </div>
        <div class="col-md-8">
            <img src='./img/qa/b1.jpg' class="img-thumbnail"/><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src='./img/qa/b2.jpg' class="img-thumbnail"/>
            <br/><br/>
            <s:iterator value="listInterlocution" var="li" status="st">
                <p><strong><s:property value="#st.count"/>.<s:property value="#li.question"/></strong></p>
                <p><img src='./img/qa/wd.jpg' class="img-thumbnail"/>
                    <s:property value="#li.answer"/>
                </p><br/><br/>
            </s:iterator>
        </div>
    </div>
</div>
<br/><br/><br/>
<!--footer-->
<div class="footer"
     style="background-color: #00933f;font-size: 15px;color:#ffffff;padding: 40px 40px 20px 40px;">
    <div style="float:left; width:25%; height:200px;"><h2>XXXXXX</h2>
        <hr align="left" width="250px"/>
        xxxxxxxxxxxx<br/>xxxxxxxxxxxx<br/>xxxxxxxxxxxx<br/></div>
    <div style="float:left; width:25%; height:200px;"><h2>XXXXXX</h2>
        <hr align="left" width="250px"/>
        xxxxxxxxxxxx<br/>xxxxxxxxxxxx<br/>xxxxxxxxxxxx<br/></div>
    <div style="float:left; width:25%; height:200px;"><h2>XXXXXX</h2>
        <hr align="left" width="250px"/>
        xxxxxxxxxxxx<br/>xxxxxxxxxxxx<br/>xxxxxxxxxxxx<br/></div>
    <div style="float:left; width:25%; height:200px;"><h2>XXXXXX</h2>
        <hr align="left" width="250px"/>
        xxxxxxxxxxxx<br/>xxxxxxxxxxxx<br/>xxxxxxxxxxxx<br/></div>
    <div>C<sup>c</sup>敬老院养老集团有限公司 粤ICP备123456号</div>
</div>
<!--自定义javascript-->
<script>
    //幻灯片功能
    $(document).ready(function () {
        $('#circleContent').carousel({interval: 3000});//每隔5秒自动轮播
    });
</script>
</body>
</html>
