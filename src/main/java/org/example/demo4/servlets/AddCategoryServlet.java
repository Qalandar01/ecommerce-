package org.example.demo4.servlets;

import org.example.demo4.entity.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/save-category")
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("categoryName");
        DB.CATEGORIES.add(new Category(categoryName));
        resp.sendRedirect("/adminChooseCat.jsp");
    }
}
