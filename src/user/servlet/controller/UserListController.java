package user.servlet.controller;

import dao.DatabaseOperation;
import user.userclass.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/userlist")
public class UserListController extends HttpServlet {
    private static int DEFULT_PAGE = 1;
    private static int DEFULT_ROWS = 5;

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException{

        int page = DEFULT_PAGE;
        int rows = DEFULT_ROWS;

        String name = req.getParameter("name") == null || req.getParameter("name").equals("") ? "" : req.getParameter("name");
        String birthplace = req.getParameter("birthplace") == null || req.getParameter("birthplace").equals("") ? "" : req.getParameter("birthplace");
        String email = req.getParameter("email") == null || req.getParameter("email").equals("") ? "" : req.getParameter("email");

        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("birthplace",birthplace);
        map.put("email",email);

        try{
            page = Integer.valueOf(req.getParameter("page"));
        }catch (NumberFormatException | NullPointerException e){ }
        try{
            rows = Integer.valueOf(req.getParameter("rows"));
        }catch (NumberFormatException | NullPointerException e){ }

        int begin = (page-1) * rows;


        List<UserInfo> users = DatabaseOperation.query(map,begin,rows);
        int total_users = DatabaseOperation.queryCount(map,begin,rows);
        //total_page current_page rows total_users
        int total_page = total_users % rows == 0 ? total_users / rows : total_users / rows + 1;

        //将查询到的数据转发至视图层
        req.setAttribute("users",users);
        req.setAttribute("total_page",total_page);
        req.setAttribute("current_page",page);
        req.setAttribute("rows",rows);
        req.setAttribute("total_users",total_users);

        req.setAttribute("query_name",name);
        req.setAttribute("query_birthplace",birthplace);
        req.setAttribute("query_email",email);

        req.getRequestDispatcher("/jsp/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException{
        doGet(req,resp);
    }
}
