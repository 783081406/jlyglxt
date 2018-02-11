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
    <title>联系我们</title>
    <meta charset="utf-8"/>
    <meta name="description" content="前端->联系我们"/>
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
                <li>
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
                <li class="active">
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
            <img src='<%=basePath %>reception/img/contact/1.png' class="img-thumbnail"/><br/>
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
<%@include file="common/footer.jsp" %>

</body>
</html>
