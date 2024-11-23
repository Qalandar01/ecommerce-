package org.example.demo4.servlets;

import org.example.demo4.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DB {
    List<Category> CATEGORIES = new ArrayList<>(List.of(
            new Category("Drinks"),
            new Category("Foods"),
            new Category("Fruits")
    ));
    List<Product> PRODUCTS = new ArrayList<>(List.of(
            new Product("Apple",1000,3,"C:/Users/LENOVO/IdeaProjects/demo4/productPhotos/apple.jpg"),
            new Product("Grape",2000,3,"C:/Users/LENOVO/IdeaProjects/demo4/productPhotos/grape.jpg"),
            new Product("Kebab",50000,2,"C:/Users/LENOVO/IdeaProjects/demo4/productPhotos/kebab.jpg"),
            new Product("Lag'mon",15000,2,"C:/Users/LENOVO/IdeaProjects/demo4/productPhotos/lagmon.jpg"),
            new Product("Fanta",12000,1,"C:/Users/LENOVO/IdeaProjects/demo4/productPhotos/fanta.jpg"),
            new Product("Moxito",13000,1,"C:/Users/LENOVO/IdeaProjects/demo4/productPhotos/moxito.jpg")
    ));
    List<User> USERS = new ArrayList<>(List.of(
            new User("Qalandar","+998914368678","asd"),
            new User("Qalandar","qwe","asd"),
            new User("Toshmat","asd","asd")
    ));
    Map<Product,Integer> basket = new HashMap<>();
    List<Order> ORDERS = new ArrayList<>();
    List<OrderItem> ORDER_ITEMS = new ArrayList<>();
}
