package com.wbg.web;

import com.wbg.entity.OrderInformation;
import com.wbg.service.OrderInformationservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/OrderInformationweb")
public class OrderInformationweb extends HttpServlet {
    OrderInformationservice orderInformationservice =new OrderInformationservice();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getParameter("action")){
            case "insert":
                response.getWriter().print(orderInformationservice.insert(request,response));
                break;
                //购物车支付
            case "zhifu":
                response.getWriter().print(orderInformationservice.update(request,response));
                break;
                //返回ajax
            case "session":
                response.getWriter().print(orderInformationservice.session(request));
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        switch (action){
            //重定向
            case "session":
               orderInformationservice.session(request,response);
                break;
            //订单支付
            case "ddzhifu":
                OrderInformation OrderInformation=new OrderInformation();
                OrderInformation= orderInformationservice.finById(request.getParameter("oid"));
                request.getSession().setAttribute("zhifuOrderInformation",OrderInformation);
               response.sendRedirect("/page/zhifu.jsp");
                break;
            //管理员查询价格
            case "admindex":
                response.getWriter().print(orderInformationservice.selectindex());
                break;
             //根据状态进行查询所有信息  当为空的时候会查询全部
            case "finall":
                String ostatus=request.getParameter("ostatus");
                if(ostatus==null)
                    ostatus="";
                response.getWriter().print(orderInformationservice.finall(ostatus));
                break;
                //根据编号进行查询
            case "finoid":
                response.getWriter().print(orderInformationservice.finoid(request.getParameter("oid")));
                break;
        }
    }
}
