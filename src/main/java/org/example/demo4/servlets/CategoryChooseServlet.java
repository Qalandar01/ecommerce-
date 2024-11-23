package org.example.demo4.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.example.demo4.entity.User.catId;

@WebServlet("/category/choose")
public class CategoryChooseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("categoryId");
        catId = Integer.parseInt(id);
        resp.sendRedirect("/menu.jsp");
    }
}
