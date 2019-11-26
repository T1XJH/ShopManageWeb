package com.wbg.service;

import com.wbg.Dao.OrderInformationDao;
import com.wbg.Util.DBUtil;
import com.wbg.Util.R;
import com.wbg.entity.OrderInformation;
import com.wbg.entity.ShoppingCart;
import com.wbg.entity.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class OrderInformationservice {
    static OrderInformationDao orderInformationDao =new OrderInformationDao();
    static R r=new R();
    //生成订单
    public String insert(HttpServletRequest request,HttpServletResponse response){
        String msg="操作失败";
        BigDecimal zj=new BigDecimal(0);
        List<ShoppingCart> list=(List<ShoppingCart>)request.getSession().getAttribute("Shopping");
        int[] sid=new int [list.size()] ;
        String[] spid=new String [list.size()] ;
        int[] count=new int[list.size()];
        for(int i=0;i<list.size();i++){
            zj=zj.add(new BigDecimal(list.get(i).getSmoney().toString()));
            sid[i]=list.get(i).getSid();
            spid[i]=list.get(i).getSpid();
            count[i]=list.get(i).getShoppingcount();
        }
        Users users=(Users) request.getSession().getAttribute("user");
        OrderInformation OrderInformation=new OrderInformation();
        OrderInformation.setAddress(Integer.parseInt(request.getParameter("address")));
        OrderInformation.setOmoney(zj);
        OrderInformation.setOuid(users.getUid());
        //地址id 价格 uid 商品pid 商品数量
        OrderInformation order= orderInformationDao.insert(OrderInformation,sid,spid,count);
        if(order!=null){
            request.getSession().setAttribute("zhifuOrderInformation",order);
            msg="ok";
        }
        return "{\"msg\":\""+msg+"\"}";
    }
    //查询价格
    public static String selectindex(){
        R r=new R();
        r.setData(orderInformationDao.selectIndex());
        return DBUtil.toJson(r);
    }
    public static OrderInformation finById(String oid){
        return orderInformationDao.finById(oid);
    }
    //支付后进入订单
    public static void session(HttpServletRequest request,HttpServletResponse response){
        //用户id
        Users user=(Users)request.getSession().getAttribute("user");
        request.getSession().setAttribute("OrderInformation", orderInformationDao.finselestatus(user.getUid(),""));

        try {
            response.sendRedirect("/page/ddxx.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //我的订单
    public static String session(HttpServletRequest request){
        //用户id
        String msg="ok";
        Users user=(Users)request.getSession().getAttribute("user");
        if(user!=null)
        request.getSession().setAttribute("OrderInformation", orderInformationDao.finselestatus(user.getUid(),""));
        else {
           msg="no";
        }
        return "{\"msg\":\""+msg+"\"}";
    }
    //修改付款
    public static String update(HttpServletRequest request,HttpServletResponse response){
       String msg="no";
        OrderInformation OrderInformation= (OrderInformation) request.getSession().getAttribute("zhifuOrderInformation");
        OrderInformation.setOstatus("已付款");
        if(orderInformationDao.updates(OrderInformation)){
            msg="ok";
        }
        return "{\"msg\":\""+msg+"\"}";
    }
    //根据状态进行查询所有订单数据
    public static String finall(String ostatus){
            r.setData(orderInformationDao.finstatusAll(ostatus));
        return DBUtil.toJson(r);
    }
    //根据订单编号查询
    public static String finoid(String oid){
        r.setData(orderInformationDao.finoid(oid));
        return DBUtil.toJson(r);
    }
    public static void main(String[] args) {
        System.out.println(orderInformationDao.finstatusAll(""));
    }
}
