<%--
  Created by IntelliJ IDEA.
  前端：首页
  User: ccjjltx
  Date: 2017/11/19
  Time: 14:09
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
    <title>首页</title>
    <meta charset="utf-8"/>
    <meta name="description" content="前端->首页"/>
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
                <li class="active"><a href="#" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;首页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
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
                <li><a href="#" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;常见问题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
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
<div class="container" style="position: relative;">
    <!--项目服务-->
    <div class="box1">
        <div class="left1"></div>
        <div class="content1"><p style="font-size: 35px;margin-bottom: 25px">
            <strong>&nbsp;&nbsp;服务项目&nbsp;&nbsp;</strong></p>
            <p style="font-size: 25px;color: gainsboro">fuwuxiangmu</p></div>
        <div class="right1"></div>
    </div>
    <div>
        <div class="row">
            <div class="col-md-3"><img src="./img/timg.png" class="img-thumbnail"><br/>
                <div style="margin-left: 25px">
                    <h3 style="color:#01a73d"><strong>1234</strong><br/><em style="padding-top: 10px">
                        <p>
                            <small>1234567891<br/>23456789</small>
                        </p>
                    </em></h3>
                </div>
            </div>
            <div class="col-md-3"><img src="./img/timg.png" class="img-thumbnail"><br/>
                <div style="margin-left: 25px">
                    <h3 style="color:#01a73d"><strong>1234</strong><br/><em style="padding-top: 10px">
                        <p>
                            <small>1234567891<br/>23456789</small>
                        </p>
                    </em></h3>
                </div>
            </div>
            <div class="col-md-3"><img src="./img/timg.png" class="img-thumbnail"><br/>
                <div style="margin-left: 25px">
                    <h3 style="color:#01a73d"><strong>1234</strong><br/><em style="padding-top: 10px">
                        <p>
                            <small>1234567891<br/>23456789</small>
                        </p>
                    </em></h3>
                </div>
            </div>
            <div class="col-md-3"><img src="./img/timg.png" class="img-thumbnail"><br/>
                <div style="margin-left: 25px">
                    <h3 style="color:#01a73d"><strong>1234</strong><br/><em style="padding-top: 10px">
                        <p>
                            <small>1234567891<br/>23456789</small>
                        </p>
                    </em></h3>
                </div>
            </div>
        </div>
    </div>

    <!--美好风采-->
    <div class="box1">
        <div class="left1"></div>
        <div class="content1"><p style="font-size: 35px;margin-bottom: 25px">
            <strong>&nbsp;&nbsp;美好风采&nbsp;&nbsp;</strong></p>
            <p style="font-size: 25px;color: gainsboro">meihaofencai</p></div>
        <div class="right1"></div>
    </div>
    <div id="mhfc" style="width:auto;height:200px;margin:60px auto;position:relative;">
        <img src="img/mm1.jpg" class="pic1"/>
        <img src="img/mm2.jpg" class="pic2"/>
        <img src="img/mm3.jpg" class="pic3"/>
        <img src="img/mm4.jpg" class="pic4"/>
        <img src="img/mm5.jpg" class="pic5"/>
        <img src="img/mm6.jpg" class="pic6"/>
        <img src="img/mm7.jpg" class="pic7"/>
        <img src="img/mm8.jpg" class="pic8"/>
        <img src="img/mm9.jpg" class="pic9"/>
        <img src="img/mm10.jpg" class="pic10"/>
    </div>
    <!--体检预约-->
    <div class="box1">
        <div class="left1"></div>
        <div class="content1"><p style="font-size: 35px;margin-bottom: 25px">
            <strong>&nbsp;&nbsp;体检预约&nbsp;&nbsp;</strong></p>
            <p style="font-size: 25px;color: gainsboro">xijianyuyue</p></div>
        <div class="right1"></div>
    </div>
    <div>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="firstname" class="col-sm-2 control-label">姓名：</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="firstname" placeholder="请输入姓名">
                </div>
                <label for="lastname" class="col-sm-2 control-label">手机号：</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="lastname" placeholder="请输入手机号">
                </div>
            </div>
            <div class="form-group">
                <label for="ename" class="col-sm-2 control-label">家长姓名：</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="ename" placeholder="请输入家长姓名">
                </div>
                <label for="age" class="col-sm-2 control-label">家长年龄：</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="age" placeholder="请输入家长年龄">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-6 col-sm-10">
                    <button type="submit" class="btn btn-default btn-lg btn-primary">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!--footer-->
<div class="footer"
     style="background-color: #00933f;font-size: 15px;color:#ffffff;padding-top: 40px;padding-bottom: 20px">
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
