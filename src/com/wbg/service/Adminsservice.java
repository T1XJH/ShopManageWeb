package com.wbg.service;

import com.wbg.Dao.AdminsDao;
import com.wbg.Util.DBUtil;
import com.wbg.Util.R;
import com.wbg.entity.Admins;

import javax.servlet.http.HttpServletRequest;

public class Adminsservice {
    static  AdminsDao adminsDao = new AdminsDao();
    static R r = new R();
   //进行密码验证
    public static String pwd(HttpServletRequest request){
        Admins admin = new Admins();
        admin.setAname(request.getParameter("aname"));
        admin.setAlpwd(request.getParameter("apwd"));
        if(adminsDao.pwd(admin)){
            r.setMsg("ok");
            request.getSession().setAttribute("admins", admin);
        }
        else
            r.setMsg("no");
        return DBUtil.toJson(r);
    }
    //查询全部数据
    public String finall(){
        r.setCount(adminsDao.count());
        r.setData(adminsDao.finAll());
        return DBUtil.toJson(r);
    }
    //根据账号查询
    public static String finname(String name,boolean bool){
        r.setData(adminsDao.finByName(name,bool));
        return DBUtil.toJson(r);
    }
    //根据状态查询
    public static String finstatus(String status){
        r.setData(adminsDao.finstatus(status));
        return DBUtil.toJson(r);
    }
    //修改状态
    public static String updastatus(int uid,String status){
        if(adminsDao.updastatus(uid,status))
            r.setMsg("修改成功");
        else r.setMsg("修改失败");
        return DBUtil.toJson(r);
    }
    // 查询用户名
    public static String findAdminName(HttpServletRequest request){
        Admins admin = (Admins) request.getSession().getAttribute("admins");
        if (admin != null){
            r.setCode(1);
            r.setMsg(admin.getAname());
        } else{
            r.setCode(0);
            r.setMsg("");
        }
        return DBUtil.toJson(r);
    }
    public static String insert(HttpServletRequest request){
        R r = new R();
        if(adminsDao.insert(admin(request)))
            r.setMsg("添加成功");
        else
            r.setMsg("添加失败");
        return DBUtil.toJson(r);
    }
    public static String update(HttpServletRequest request){
        R r = new R();
        if(adminsDao.update(admin(request)))
            r.setMsg("修改成功");
        else
            r.setMsg("修改失败");
        return DBUtil.toJson(r);
    }
    public static Admins admin(HttpServletRequest request){
        Admins admin = new Admins();
        if (request.getParameter("aid") != null)
            admin.setAid(Integer.parseInt(request.getParameter("aid")));
        admin.setAname(request.getParameter("aname"));
        admin.setAlname(request.getParameter("alname"));
        admin.setAlpwd(request.getParameter("alpwd"));
        admin.setAstatus("可用");
        return admin;
    }
    public static String delete(HttpServletRequest request){
        R r = new R();
        if(adminsDao.delete(new Admins(Integer.parseInt(request.getParameter("aid")))))
            r.setMsg("删除成功");
        else
            r.setMsg("删除失败");
        return DBUtil.toJson(r);
    }

}
