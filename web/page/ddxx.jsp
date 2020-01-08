<%@ page import="com.wbg.entity.OrderInformation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
    <link rel="stylesheet" href="../css/ext.css"/>
</head>
<body>
<div class="container">
    <div class="row box" style="margin-bottom: 2rem; margin-top: 2rem;">
        <div class="col-md-10 column">
            <h1 style="color: red; margin: auto;">订单中心</h1>
        </div>
        <div class="col-md-2 column" align="right">
            <a href="/index">返回首页</a>
        </div>
    </div>
    <div class="row well well-sm">
        <div class="col-md-2 column" style="text-align: center" align="left">
        </div>
        <div class="col-md-3 column" align="center">订单详情</div>
        <div class="col-md-2 column" align="center"></div>
        <div class="col-md-1 column" align="center">收货人</div>
        <div class="col-md-2 column" align="center">订单总金额</div>
        <div class="col-md-2 column" align="center">操作</div>
    </div>
    <!-- 购物车列表 -->
    <% List<OrderInformation> list = (List<OrderInformation>)session.getAttribute("OrderInformation");
        list = list != null ? list : new ArrayList<OrderInformation>();
        for(OrderInformation OrderInformation : list){ %>
    <div class="row well well-sm box">
        <div class="col-md-2 column" style="text-align: center" align="left">
            <p style="font-size: 12px;"><%= OrderInformation.getOtime() %></p>
            <img alt="80x80" width="60rem" height="50rem" src="/<%=OrderInformation.getPimg()%>" />
        </div>
        <div class="col-md-3 column" align="left">
            <p>
               <%= OrderInformation.getPname() %>
            </p>

        </div>
        <div class="col-md-2 column" align="center">
            <p style="font-size: 12px;margin-top: -20px">订单号</p>
            <p style="font-size: 12px;"><%= OrderInformation.getOid() %></p>
        </div>
        <div class="col-md-1 column" align="center">
            <p style="font-size: 12px;"><%= OrderInformation.getUnameu() %></p>
        </div>
        <div class="col-md-2 column" align="center">
            <p style="color: red; font-size: 13px">¥ <%= OrderInformation.getOmoney() %></p>
        </div>
        <div class="col-md-2 column" align="center">
            <%
                if(OrderInformation.getOstatus().equals("未付款")){  %>
            <a style="font-size: 15px;" href="/OrderInformationweb?action=ddzhifu&&oid=<%= OrderInformation.getOid() %>"><%= OrderInformation.getOstatus() %></a>
            <% }else{ %>
            <p style="font-size: 15px;color: #337ab7"><%=OrderInformation.getOstatus()%></p>
            <% }%>


        </div>
    </div>
    <% } %>
</div>
</div>
</body>
<script src="../js/operation.js"></script>
</html>

