package user.servlet.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginStatusUtils {
    public static boolean checkLogin( HttpServletRequest req, HttpServletResponse resp ) throws IOException{
        Object access = req.getSession().getAttribute("access");
        if(access == null || !access.toString().equals("allow")){
            //未登录用户直接跳转登录页面
            resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");
            return true;
        }
        return false;
    }
}
