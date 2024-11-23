package org.example.demo4.servlets;


import org.example.demo4.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        Optional<User> first = DB.USERS.stream().filter(user -> user.getPhone().equals(phone) && user.getPassword().equals(password)).findFirst();
        if (first.isPresent()){
            if (first.get().getPhone().equals("+998914368678") && first.get().getPassword().equals("asd")){
                resp.addCookie(new Cookie("admin","Thisisadmin"));
                resp.sendRedirect("/adminChooseCat.jsp");
            }else {
                resp.addCookie(new Cookie("user","Thisisuser"));
                resp.addCookie(new Cookie("userId",first.get().getId().toString()));
                resp.sendRedirect("/menu.jsp");
            }
        }
        else {
            resp.sendRedirect("/login.jsp");
        }
    }
}
