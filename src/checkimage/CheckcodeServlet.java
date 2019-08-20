package checkimage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkcodeServlet")
public class CheckcodeServlet extends HttpServlet {

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String checkCode = CheckImageUtil.getCheckcode();

        response.setContentType(CheckImageUtil.getContextType());
        CheckImageUtil.writeCheckImage(checkCode,response.getOutputStream());
        request.getSession().setAttribute("checkcode",checkCode);
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        doPost(request,response);
    }

}
