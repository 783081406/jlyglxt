<%--
  Created by IntelliJ IDEA.
  前端：入住费用
  User: ccjjltx
  Date: 2017/11/24
  Time: 13:20
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
    <title>入住费用</title>
    <meta charset="utf-8"/>
    <meta name="description" content="前端->入住费用"/>
    <meta name="author" content="陈彩君"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="./css/img.css"/>
    <script type="text/javascript" src="../jquery/jquery-3.0.0.min.js"></script>
    <script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
    <style>
        #costtable tr td {
            vertical-align: middle;
            text-align: center;
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
                <li>
                    <a href="<%=basePath %>reception/environment.action" class="text-center" style="font-size: 16px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生活环境&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                        <small style="font-size: 11px">Environment</small>
                    </a></li>
                <li class="active">
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
<div class="container" style="margin-top: 50px;">
    <div class="row">
        <div class="col-md-3">
            <p>关于我们</p>
            <p>Cc敬老院在广州打造的第一所社区嵌入式精品养老服务机构， 2015年正式运营。提供生活照料、康复护理、紧急救援、
                短期托养、居家服务、医疗保健、精神慰藉等综合性养老服务。</p>
            <p>搭车线路</p>
            <p>地址：广州市从化区太平镇123大街123号
                地铁：A1站、A2站
                公交线路：69路、130路、184路、190路、226路、高峰快线39路、250路、14路、206路、229路、239路、264路、270路、37路、93路、468路</p>
        </div>
        <div class="col-md-9">
            <img src='./img/cost/c1.jpg' class="img-thumbnail"/><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src='./img/cost/c2.jpg' class="img-thumbnail"/><br/><br/>
            <img src='./img/cost/c3.jpg' class="img-thumbnail"/>
            <br/>
            <table class="table table-bordered" id="costtable">
                <caption>入住费用</caption>
                <tr>
                    <td rowspan="4">收费标准</td>
                    <td>入住费(元/人/月)</td>
                    <td>伙食费(元/人/月)</td>
                    <td>护理费(元/人/月)</td>
                    <td>合计(元/人/月)</td>
                </tr>
                <tr>
                    <td>标准：${roomcost1}</td>
                    <td rowspan="3">${minimum}-${highest}</td>
                    <td>初级护理：${nursingfee1}</td>
                    <td>${ctotal11}-${ctotal12}</td>
                </tr>
                <tr>
                    <td>中等：${roomcost2}</td>
                    <td>中级护理：${nursingfee2}</td>
                    <td>${ctotal21}-${ctotal22}</td>
                </tr>
                <tr>
                    <td>豪华：${roomcost3}</td>
                    <td>高级护理：${nursingfee3}</td>
                    <td>${ctotal31}-${ctotal32}</td>
                </tr>
                <tr class="active">
                    <td>房间配置</td>
                    <td colspan="4">房内均配有独立阳台、独立卫生间、闭路电视、高级家俱、冷暖空调</td>
                </tr>
                <tr class="success">
                    <td>护理级别</td>
                    <td>初级护理一般照顾护理</td>
                    <td>中级护理半照顾护理</td>
                    <td colspan="2">高级护理专人特殊照顾护理</td>
                </tr>
                <tr class="warning">
                    <td>费用计算</td>
                    <td colspan="4">总费用=入住费+伙食费+护理费</td>
                </tr>
                <tr class="danger">
                    <td>费用包含</td>
                    <td colspan="4">以上费用为每人每月全包收费价格，入住不再另行收取任何其他费用</td>
                </tr>
                <tr>
                    <td>入住条件</td>
                    <td colspan="4">最少入住时间三个月。押金两千元。押金在入住期满予以全额退还，也可作为最后一个月的收费予以抵扣</td>
                </tr>
            </table>
            <br/><br/>
            <p>一、参加现场免费参观的人员，可免费享受试住三天的真实体验；</p>
            <p> 二、一次性缴纳六个月费用，可享受每月款项98折的优惠；</p>
            <p>三、一次性缴纳十二个月费用，可享受每月款项95折的优惠；</p>
            <p>四、一次性缴纳三十六个月费用，可享受每月款项9折的优惠； </p>
            <br/><br/>
            <p>费用情况按可长者实际需求确定 详细请致电:</p>
            <p>电话：020-12345678</p>
            <p>联系手机 ：(86)12345678911（C院长）</p>
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
