package user.servlet.controller;

import dao.DatabaseOperation;
import org.apache.commons.beanutils.BeanUtils;
import user.userclass.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        if (LoginStatusUtils.checkLogin(request, response)) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue()[0]);
        }
        UserInfo user = new UserInfo();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        DatabaseOperation.update(user);
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

        response.setContentType("text/html; charset=utf-8");
        String uid = request.getParameter("uid");
        System.out.println("要查询的uid:" + uid);
        if (uid == null) {
            String referer = request.getHeader("referer");
            response.sendRedirect(referer == null ? request.getContextPath() + "/jsp/login.jsp" : referer);
        }

        UserInfo user = DatabaseOperation.queryForUser(uid);
        if (user == null) {
            response.getWriter().write("<h1>您要修改的用户不存在</h1>");
            return;
        }
        //查询到的用户转发至视图层
        request.setAttribute("user", user);
        request.getRequestDispatcher("/jsp/update.jsp").forward(request, response);
    }
}
