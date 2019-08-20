package user.servlet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DatabaseLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> map = new HashMap<>();
        //验证码校验
        String checkcode = request.getParameter("checkcode");
        HttpSession session = request.getSession();
        Object ccObj = session.getAttribute("checkcode");
        String old_checkcode = null;
        if(ccObj != null){
            old_checkcode = ccObj.toString().toLowerCase();
        }

        session.removeAttribute("checkcode");
        if (checkcode == null || !checkcode.toLowerCase().equals(old_checkcode)){
            map.put("success","failed");
            map.put("msg","验证码错误");
        }else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if ((username == null || username.isEmpty()) ||
                    (password == null || password.isEmpty()) ||
                    DatabaseLogin.login(request.getParameter("username"), request.getParameter("password")) == null) {
                map.put("success", "failed");
                map.put("msg", "用户名或密码错误");
            } else {
                String remember = request.getParameter("remember");
                if (remember != null && remember.equals("on")) {
                    Cookie jsessionid = new Cookie("JSESSIONID", session.getId());
                    jsessionid.setMaxAge(60 * 60 * 24);
                    response.addCookie(jsessionid);
                }
                session.setAttribute("access", "allow");
                map.put("success", "success");
            }
        }
        String json = objectMapper.writeValueAsString(map);
        response.getWriter().write(json);

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        doPost(request,response);
    }
}
