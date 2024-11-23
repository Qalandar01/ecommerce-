package org.example.demo4.servlets;

import org.example.demo4.auth.AuthService;
import org.example.demo4.entity.Order;
import org.example.demo4.entity.OrderItem;
import org.example.demo4.entity.Product;
import org.example.demo4.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static org.example.demo4.servlets.DB.basket;

@WebServlet("/basket/confirm")
public class ConfirmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> user = AuthService.loggedUser(req);
        if (user.isEmpty()){
            resp.sendRedirect("/login.jsp");
            return;
        }
        Order order = new Order();
        for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
            OrderItem orderItem = new OrderItem(
                    order.getId(),
                    entry.getKey().getId(),
                    entry.getValue()
            );
            DB.ORDER_ITEMS.add(orderItem);
        }
        order.setUserId(user.get().getId());
        DB.ORDERS.add(order);
        basket.clear();
        resp.sendRedirect("/menu.jsp");
    }
}
