package org.example.demo4.servlets;


import org.example.demo4.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/img/*")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String productId = pathInfo.split("/")[1];
        Product product1 = DB.PRODUCTS.stream().filter(product -> product.getId().equals(Integer.parseInt(productId))).findFirst().get();
        resp.getOutputStream().write(Files.readAllBytes(Path.of(product1.getPhotoUrl())));

    }
}
