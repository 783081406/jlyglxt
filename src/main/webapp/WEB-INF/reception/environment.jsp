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
    <%@include file="common/resourceurl.jsp" %>
</head>
<body>
<!--头文件-->
<%@include file="common/header.jsp" %>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"></a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li>
                    <a href="<%=basePath %>reception/index" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;首页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                        <small style="font-size: 11px">Home</small>
                    </a></li>
                <li class="active">
                    <a href="<%=basePath %>reception/environment" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生活环境&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                        <small style="font-size: 11px">Environment</small>
                    </a></li>
                <li>
                    <a href="<%=basePath %>reception/cost" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;入住费用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                        <small style="font-size: 11px">Cost</small>
                    </a></li>
                <li>
                    <a href="<%=basePath %>reception/qa" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;常见问题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                        <small style="font-size: 11px">Q&A</small>
                    </a></li>
                <li>
                    <a href="<%=basePath %>reception/contact" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系我们&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
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
<%@include file="common/billboards.jsp"%>

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
            <div class="col-md-6"><img src='<%=basePath %>reception/img/environment/1.jpg' class="img-thumbnail"></div>
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
        <div class="col-md-3"><img src='<%=basePath %>reception/img/environment/img001.jpg' class="img-thumbnail"><br/>
            <p class="text-center">小憩之地 饱含自然意蕴</p></div>
        <div class="col-md-3"><img src='<%=basePath %>reception/img/environment/img002.jpg' class="img-thumbnail"><br/>
            <p class="text-center">绿树掩映的园林式院落</p></div>
        <div class="col-md-3"><img src='<%=basePath %>reception/img/environment/img003.jpg' class="img-thumbnail"><br/>
            <p class="text-center">绿树环抱的亭台楼阁</p></div>
        <div class="col-md-3"><img src='<%=basePath %>reception/img/environment/img004.jpg' class="img-thumbnail"><br/>
            <p class="text-center">爷孙共享天伦之乐</p></div>
    </div>
    <br/><br/><br/>
    <div class="row">
        <div class="col-md-3"><img src='<%=basePath %>reception/img/environment/img005.jpg' class="img-thumbnail"><br/>
            <p class="text-center">干净舒适的老人房间</p></div>
        <div class="col-md-3"><img src='<%=basePath %>reception/img/environment/img006.jpg' class="img-thumbnail"><br/>
            <p class="text-center">明亮舒适的老人房间</p></div>
        <div class="col-md-3"><img src='<%=basePath %>reception/img/environment/img007.jpg' class="img-thumbnail"><br/>
            <p class="text-center">棋牌麻将应有尽有</p></div>
        <div class="col-md-3"><img src='<%=basePath %>reception/img/environment/img008.jpg' class="img-thumbnail"><br/>
            <p class="text-center">静谧绿园中，相扶相拌温存永久</p></div>
    </div>
</div>
<br/><br/><br/>

<!--footer-->
<%@include file="common/footer.jsp" %>

</body>
</html>
