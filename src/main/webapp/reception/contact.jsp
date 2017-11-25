<%--
  Created by IntelliJ IDEA.
  前端：联系我们
  User: ccjjltx
  Date: 2017/11/23
  Time: 22:59
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
    <meta name="description" content="前端->联系我们"/>
    <meta name="author" content="陈彩君"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="./css/img.css"/>
    <script type="text/javascript" src="../jquery/jquery-3.0.0.min.js"></script>
    <script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
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
                <li>
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
                <li class="active">
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
<div class="container" style="margin-top: 50px;">
    <div class="row">
        <div class="col-md-4">
            <p>关于我们</p>
            <p>Cc敬老院在广州打造的第一所社区嵌入式精品养老服务机构， 2015年正式运营。提供生活照料、康复护理、紧急救援、
                短期托养、居家服务、医疗保健、精神慰藉等综合性养老服务。</p>
            <p>搭车线路</p>
            <p>地址：广州市从化区太平镇123大街123号
                地铁：A1站、A2站
                公交线路：69路、130路、184路、190路、226路、高峰快线39路、250路、14路、206路、229路、239路、264路、270路、37路、93路、468路</p>
        </div>
        <div class="col-md-8">
            联系我们<br/>
            <img src='./img/contact/1.png' class="img-thumbnail"/><br/>
            C<sup>c</sup>敬老院<br/>
            咨询服务电话：020-12345678<br/>
            地址：广州市从化区太平镇123大街123号<br/>
            联系手机：12345678911（C院长）<br/>
            邮箱：123456789@qq.com<br/>
            传真号码：020-12345679<br/>
            邮政编码：516171<br/>
            欢迎老人，家属来院参观！周边地区长者参观入住，我们专车免费负责接送<br/>
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
