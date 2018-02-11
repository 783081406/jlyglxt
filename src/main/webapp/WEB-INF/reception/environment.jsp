<%--
  Created by IntelliJ IDEA.
  前端：环境设施
  User: ccjjltx
  Date: 2017/11/23
  Time: 13:43
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
    <title>环境设施</title>
    <meta charset="utf-8"/>
    <meta name="description" content="前端->环境设施"/>
    <meta name="author" content="陈彩君"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
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
                <li>
                    <a href="<%=basePath %>reception/index.action" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;首页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                        <small style="font-size: 11px">Home</small>
                    </a></li>
                <li class="active">
                    <a href="<%=basePath %>reception/environment.action" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生活环境&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                        <small style="font-size: 11px">Environment</small>
                    </a></li>
                <li>
                    <a href="<%=basePath %>reception/cost.action" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;入住费用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                        <small style="font-size: 11px">Cost</small>
                    </a></li>
                <li>
                    <a href="<%=basePath %>reception/qa.action" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;常见问题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                        <small style="font-size: 11px">Q&A</small>
                    </a></li>
                <li>
                    <a href="<%=basePath %>reception/contact.action" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系我们&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
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
    <!--环境介绍-->
    <div class="box1">
        <div class="left1"></div>
        <div class="content1"><p style="font-size: 35px;margin-bottom: 25px">
            <strong>&nbsp;&nbsp;环境介绍&nbsp;&nbsp;</strong></p>
            <p style="font-size: 25px;color: gainsboro">Introduce</p></div>
        <div class="right1"></div>
    </div>
    <div>
        <div class="row">
            <div class="col-md-6"><h2>敬老院介绍</h2>
                <hr align="left" width="200px" style="height:5px;background-color: #ed6c00"/>
                <p style="font-size: 15px">
                    地理位置得天独厚，生活医疗配套齐全。C<sup>c</sup>敬老院位于广州从化中心城区，依山而建，
                    园林景观，动静皆宜。本院占地面积48000平方米，绿化面积达八成，信步院内小道，犹如置于郊野林中，
                    可自由的享受与大自然相融的乐趣，非常适合长者居住。</p><br/>
                <p style="font-size: 17px">
                    C<sup>c</sup>敬老院在广州打造的第一所社区嵌入式精品养老服务机构，
                    2015年正式运营。提供生活照料、康复护理、紧急救援、短期托养、居家服务、医疗保健、精神慰藉等综合性养老服务。
                </p><br/>
                <p style="font-size: 15px">
                    自开业以来，得了到社会各界的高度关注和认可。
                </p><br/>
            </div>
            <div class="col-md-6"><img src='./img/environment/1.jpg' class="img-thumbnail"></div>
        </div>
    </div>
    <!--项目服务-->
    <div class="box1">
        <div class="left1"></div>
        <div class="content1"><p style="font-size: 35px;margin-bottom: 25px">
            <strong>&nbsp;&nbsp;环境设施&nbsp;&nbsp;</strong></p>
            <p style="font-size: 25px;color: gainsboro">Facilities</p></div>
        <div class="right1"></div>
    </div>
    <div class="row">
        <div class="col-md-3"><img src='./img/environment/img001.jpg' class="img-thumbnail"><br/>
            <p class="text-center">小憩之地 饱含自然意蕴</p></div>
        <div class="col-md-3"><img src='./img/environment/img002.jpg' class="img-thumbnail"><br/>
            <p class="text-center">绿树掩映的园林式院落</p></div>
        <div class="col-md-3"><img src='./img/environment/img003.jpg' class="img-thumbnail"><br/>
            <p class="text-center">绿树环抱的亭台楼阁</p></div>
        <div class="col-md-3"><img src='./img/environment/img004.jpg' class="img-thumbnail"><br/>
            <p class="text-center">爷孙共享天伦之乐</p></div>
    </div>
    <br/><br/><br/>
    <div class="row">
        <div class="col-md-3"><img src='./img/environment/img005.jpg' class="img-thumbnail"><br/>
            <p class="text-center">干净舒适的老人房间</p></div>
        <div class="col-md-3"><img src='./img/environment/img006.jpg' class="img-thumbnail"><br/>
            <p class="text-center">明亮舒适的老人房间</p></div>
        <div class="col-md-3"><img src='./img/environment/img007.jpg' class="img-thumbnail"><br/>
            <p class="text-center">棋牌麻将应有尽有</p></div>
        <div class="col-md-3"><img src='./img/environment/img008.jpg' class="img-thumbnail"><br/>
            <p class="text-center">静谧绿园中，相扶相拌温存永久</p></div>
    </div>
</div>
<br/><br/><br/>
<!--footer-->
<div class="footer"
     style="background-color: #00933f;font-size: 15px;color:#ffffff;padding: 40px 40px 20px 40px;">
    <div style="float:left; width:25%; height:200px;"><h2>C<sup>c</sup>控股</h2>
        <hr align="left" width="245px"/>
        Aa集团有限公司<br/>Bb集团有限公司<br/>Dd集团有限公司<br/>Ee集团有限公司<br/></div>
    <div style="float:left; width:25%; height:200px;"><h2>服务项目</h2>
        <hr align="left" width="245px"/>
        生活丰富<br/>营养膳食<br/>医疗服务<br/>专业物理护理<br/></div>
    <div style="float:left; width:25%; height:200px;"><h2>联系我们</h2>
        <hr align="left" width="245px"/>
        电话：020-12345678<br/>地址：广州市从化区太平镇123大街123号<br/>联系手机：12345678911（C院长）<br/>邮箱：123456789@qq.com<br/></div>
    <div style="float:left; width:25%; height:200px;">
        <img src='./img/foot/erweima.jpg' class="img-thumbnail">
    </div>
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
