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
                <li class="active">
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
<div class="container" style="margin-top: 50px;">
    <div class="row">
        <div class="col-md-4" style="background-color: #91d945;padding-top: 50px;padding-right:22px;padding-bottom: 50px">
            <div class="row">
                <div class="col-md-12"><span style="color: #ffffff;font-size: 18px;">老年活动</span>
                    <hr style="height:5px;background-color: #ffffff"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6"><img src='<%=basePath %>reception/img/qa/a01.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">舒筋活络</span></p></div>
                <div class="col-md-6"><img src='<%=basePath %>reception/img/qa/a02.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">报刊阅读</span></p></div>
            </div>
            <div class="row">
                <div class="col-md-6"><img src='<%=basePath %>reception/img/qa/a03.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">一手好字</span></p></div>
                <div class="col-md-6"><img src='<%=basePath %>reception/img/qa/a04.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">早上空气清新</span></p></div>
            </div>
            <div class="row">
                <div class="col-md-6"><img src='<%=basePath %>reception/img/qa/a05.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">拉一曲二胡</span></p></div>
                <div class="col-md-6"><img src='<%=basePath %>reception/img/qa/a06.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">踢毽子灵活肢体</span></p></div>
            </div>
            <div class="row">
                <div class="col-md-6"><img src='<%=basePath %>reception/img/qa/a07.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">晨练中的老人</span></p></div>
                <div class="col-md-6"><img src='<%=basePath %>reception/img/qa/a08.jpg' class="img-thumbnail"/><br/>
                    <p class="text-center"><span style="color: #ffffff; ">练练手力</span></p></div>
            </div>
        </div>
        <div class="col-md-8">
            <img src='<%=basePath %>reception/img/qa/b1.jpg' class="img-thumbnail"/><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src='<%=basePath %>reception/img/qa/b2.jpg' class="img-thumbnail"/>
            <br/><br/>
            <s:iterator value="listInterlocution" var="li" status="st">
                <p><strong><s:property value="#st.count"/>.<s:property value="#li.question"/></strong></p>
                <p><img src='<%=basePath %>reception/img/qa/wd.jpg' class="img-thumbnail"/>
                    <s:property value="#li.answer"/>
                </p><br/><br/>
            </s:iterator>
        </div>
    </div>
</div>
<br/><br/><br/>

<!--footer-->
<%@include file="common/footer.jsp" %>

</body>
</html>
