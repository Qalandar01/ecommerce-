package org.example.demo4.servlets;

import org.example.demo4.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-amount")
public class UpdateAmountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("productId");
        Product product1 = DB.PRODUCTS.stream().filter(product -> product.getId().equals(Integer.parseInt(id))).findFirst().get();
        String action = req.getParameter("action");
        Integer value = DB.basket.get(product1);
        if (action.equals("increase")){
            value++;
            DB.basket.put(product1,value);
        }else {
            value--;
            DB.basket.put(product1,value);
        }
        if (value == 0){
            DB.basket.remove(product1);
        }
        resp.sendRedirect("/basket.jsp");
    }
}
