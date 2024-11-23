package org.example.demo4.servlets;

import org.example.demo4.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/add/basket")
public class AddToBasket extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer productId = Integer.parseInt(req.getParameter("productId"));
        Optional<Product> optionalProduct = DB.PRODUCTS.stream().filter(product -> product.getId().equals(productId)).findFirst();
        if (optionalProduct.isPresent()) {
            if (DB.basket.containsKey(optionalProduct.get())) {
                DB.basket.remove(optionalProduct.get());
            }else {
                DB.basket.put(optionalProduct.get(),1);
            }
        }
        resp.sendRedirect("/menu.jsp");
    }
}
