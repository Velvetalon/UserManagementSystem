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

@WebServlet("/addUser")
public class AddUser extends HttpServlet {
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

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
        DatabaseOperation.addUser(user.getName(),user.getGender(), Integer.parseInt(user.getAge()),user.getBirthplace(),user.getQq_number(),user.getEmail());

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        doPost(request,response);
    }
}
