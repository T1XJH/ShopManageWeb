<%@ page import="com.wbg.entity.ProductType" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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

