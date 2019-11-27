<%@ page import="com.wbg.entity.OrderInformation" %>
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
            <h1 style="color: red; margin: auto;">支付中心</h1>
        </div>
        <div class="col-md-2 column" align="right">
            <a href="/index">返回首页</a>
        </div>
    </div>
</div>
<%
    OrderInformation OrderInformation= (OrderInformation) session.getAttribute("zhifuOrderInformation");
%>
<div class="row well well-sm box">
    <div class="col-md-4 column" style="text-align: center" align="left">
        <p style="font-size: 20px;"><%=orderInformation.getOtime()%></p>
    </div>
    <div class="col-md-4 column" align="center">
        <p style="font-size: 20px;">订单号:<%=orderInformation.getOid()%></p>
    </div>
    <div class="col-md-4 column" align="left">
        <p style="color: red; font-size: 20px">¥ <%=orderInformation.getOmoney()%></p>
    </div>
</div>
<div class="modal-body">
    <div class="row" style="margin: 1rem;">
        <div class="col-md-12">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <!--<label for="password" style="font-size: large;">密 码</label>-->
                    <input type="password" class="form-control" id="password" placeholder="密 码"/>
                    <button type="button" style="width: 100%;margin-top: 5%;" id="zhifuok"class="btn btn-primary">确认支付</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/operation.js"></script>
<script>
    $("#zhifuok").click(function () {
        $.ajax({
            type: "post",
            url: "/OrderInformationweb?action=zhifu",
            dataType: "json",
            success:function (data) {
                if(data.msg == "ok"){
                    alert("支付成功");
                    window.location.href="/OrderInformationweb?action=session";
                }
                else {
                    alert("支付失败");
                }
            }
        })
    })
</script>
</body>

</html>

