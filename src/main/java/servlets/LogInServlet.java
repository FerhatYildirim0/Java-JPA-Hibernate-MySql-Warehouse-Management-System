package servlets;


import utils.DBUtil;
import utils.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="loginServlet", value="/login-servlet")
public class LogInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String remember = req.getParameter("rememberMe");
        System.out.println("MD5 lı olması gereken şifre : " + password);
        System.out.println("Email :" +email+ " Password :"+password+" Remember Status :" + remember);
        DBUtil dbUtil = new DBUtil();
        boolean status = dbUtil.login(email,password,remember,req,resp);
        if (status){
            resp.sendRedirect("dashboard.jsp");
        }else{
            req.setAttribute("LoginError", "Email yada şifre hatalı!" );
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
