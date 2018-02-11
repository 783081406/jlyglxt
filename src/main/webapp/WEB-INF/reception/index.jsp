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
                <li class="active">
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
    <!--项目服务-->
    <div class="box1">
        <div class="left1"></div>
        <div class="content1"><p style="font-size: 35px;margin-bottom: 25px">
            <strong>&nbsp;&nbsp;服务项目&nbsp;&nbsp;</strong></p>
            <p style="font-size: 25px;color: gainsboro">Service Items</p></div>
        <div class="right1"></div>
    </div>
    <div>
        <div class="row">
            <s:iterator value="listServiceitems" var="si">
                <div class="col-md-3"><img src='<%=basePath %>reception/img/service/<s:property value="#si.spath"/>'
                                           class="img-thumbnail">
                    <div style="margin-left: 25px">
                        <h3 style="color:#01a73d"><strong><s:property value="#si.stitle"/></strong><br/>
                            <p style="padding-top: 10px">
                                <small><s:property value="#si.scontent"/></small>
                            </p>
                        </h3>
                    </div>
                </div>
            </s:iterator>
        </div>
    </div>

    <!--美好风采-->
    <div class="box1">
        <div class="left1"></div>
        <div class="content1"><p style="font-size: 35px;margin-bottom: 25px">
            <strong>&nbsp;&nbsp;美好风采&nbsp;&nbsp;</strong></p>
            <p style="font-size: 25px;color: gainsboro">Beautiful style</p></div>
        <div class="right1"></div>
    </div>
    <div id="mhfc" style="width:auto;height:200px;margin:60px auto;position:relative;">
        <img src="<%=basePath %>reception/img/qa/a01.jpg" class="pic1"/>
        <img src="<%=basePath %>reception/img/qa/a02.jpg" class="pic2"/>
        <img src="<%=basePath %>reception/img/qa/a03.jpg" class="pic3"/>
        <img src="<%=basePath %>reception/img/qa/a04.jpg" class="pic4"/>
        <img src="<%=basePath %>reception/img/qa/a05.jpg" class="pic5"/>
        <img src="<%=basePath %>reception/img/qa/a06.jpg" class="pic6"/>
        <img src="<%=basePath %>reception/img/qa/a07.jpg" class="pic7"/>
        <img src="<%=basePath %>reception/img/qa/a08.jpg" class="pic8"/>
        <img src="<%=basePath %>reception/img/qa/a01.jpg" class="pic9"/>
        <img src="<%=basePath %>reception/img/qa/a02.jpg" class="pic10"/>
    </div>
    <!--体检预约-->
    <div class="box1">
        <div class="left1"></div>
        <div class="content1"><p style="font-size: 35px;margin-bottom: 25px">
            <strong>&nbsp;&nbsp;体验预约&nbsp;&nbsp;</strong></p>
            <p style="font-size: 25px;color: gainsboro">Experience Order</p></div>
        <div class="right1"></div>
    </div>
    <div>
        <form class="form-horizontal" action="<%=basePath %>bespeakaction/saveInformation" method="post"
              role="form">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">姓名：</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
                </div>
                <label for="phone" class="col-sm-2 control-label">手机号：</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号">
                </div>
            </div>
            <div class="form-group">
                <label for="ename" class="col-sm-2 control-label">家长姓名：</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="ename" name="ename" placeholder="请输入家长姓名">
                </div>
                <label for="eage" class="col-sm-2 control-label">家长年龄：</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="eage" name="eage" placeholder="请输入家长年龄">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-6 col-sm-10">
                    <input type="submit" class="btn btn-default btn-lg btn-primary"/>
                </div>
            </div>
        </form>
    </div>
</div>

<!--footer-->
<%@include file="common/footer.jsp" %>

<!--自定义javascript-->
<script>
    //幻灯片功能
    $(document).ready(function () {
        $('#circleContent').carousel({interval: 3000});//每隔5秒自动轮播
    });
</script>
</body>
</html>
