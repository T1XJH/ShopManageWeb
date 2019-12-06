package com.wbg.web;

import com.wbg.entity.Admins;
import com.wbg.service.Adminsservice;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
@WebServlet("/adminsweb")
public class Adminsweb extends javax.servlet.http.HttpServlet {
    static Adminsservice adminsservice=new Adminsservice();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert":
                response.getWriter().print(Adminsservice.insert(request));
                break;
            case "update":
                response.getWriter().print(Adminsservice.update(request));
                break;
            case "delete":
                response.getWriter().print(Adminsservice.delete(request));
                break;
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            //密码验证
            case "pwd":
                response.getWriter().print(adminsservice.pwd(request));
                break;
                //全部查询
            case "finAll":
                response.getWriter().print(adminsservice.finall());
                break;
                //状态查询
            case "finstatus":
                response.getWriter().print(adminsservice.finstatus(request.getParameter("astatus")));
                break;
            case "finname":
                //根据账号查询   判断转过来是否为true
                response.getWriter().print(adminsservice.finname(request.getParameter("aname"),request.getParameter("mhcx").equals("true")));
                break;
                //修改状态
            case "updastatus":
                response.getWriter().print(adminsservice.updastatus(Integer.parseInt(request.getParameter("uid")),request.getParameter("status")));
                break;
            case "admname":
                response.getWriter().print(adminsservice.findAdminName(request));
                break;
            case "logout":
                request.getSession().setAttribute("admins", null);
                break;

        }
    }
}
