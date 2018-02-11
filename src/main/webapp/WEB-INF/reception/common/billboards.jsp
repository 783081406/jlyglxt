<%--
  Created by IntelliJ IDEA.
  前端->宣传栏
  User: ccjjltx
  Date: 2018/2/11
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <img alt="" src='<%=basePath %>reception/img/billboards/<s:property value="#bb.bpath"/>'/>
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

<!--宣传栏javascript-->
<script>
    //幻灯片功能
    $(document).ready(function () {
        $('#circleContent').carousel({interval: 3000});//每隔5秒自动轮播
    });
</script>