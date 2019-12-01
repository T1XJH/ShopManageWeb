package com.wbg.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.wbg.service.Adminsservice;

@WebServlet("/admin")
public class admin extends HttpServlet {
    static Adminsservice adminsservice=new Adminsservice();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = true;
        String action = request.getParameter("action");
        switch (action){
            case "admname":
                response.getWriter().print(adminsservice.findAdminName(request));
                flag = false;
                break;
        }
        if(flag){
            response.sendRedirect("/adminTemplate/login.html");
        }

    }
}
