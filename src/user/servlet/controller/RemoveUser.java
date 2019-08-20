package user.servlet.controller;

import dao.DatabaseOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeUser")
public class RemoveUser extends HttpServlet {
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String[] uids = request.getParameterValues("uid[]");
        if (uids == null) {
            return;
        }

        for (String uid : uids) {
            DatabaseOperation.remove(uid);
        }
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        response.sendError(418, "I'm a teapot");
    }
}
