package org.example.demo4.servlets;

import org.example.demo4.auth.AuthService;
import org.example.demo4.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/orders")
public class UserOrders extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> user = AuthService.loggedUser(req);
        String  userId = "";
        if (user.isEmpty()){
            resp.sendRedirect("/login.jsp");
        }else {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("userId")){
                    userId = cookie.getValue();
                }
            }
            resp.sendRedirect("/user-orders.jsp?userId=" +userId);
        }


    }
}
