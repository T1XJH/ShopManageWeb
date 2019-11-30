<%@ page import="com.wbg.entity.ProductType" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 邦杠
  Date: 2018/8/29
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav navcolor nav-pills nav-stacked" id="leftadd">
   <% List<ProductType> list = (List<ProductType>) session.getAttribute("productType");
            list = list != null ? list : new ArrayList<ProductType>();
            for (ProductType product : list) {%>
    <li class="nav" id="active<%=product.getTid()%>">
        <a href="/Productweb?action=session&ptid=<%=product.getTid()%>"><%=product.getTname()%></a>
    </li>
    <%
        }
    %>
</ul>

