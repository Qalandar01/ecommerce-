package org.example.demo4.auth;

import org.example.demo4.entity.User;
import org.example.demo4.servlets.DB;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AuthService {
    public static boolean isAdmin(HttpServletRequest request){
        boolean isAdmin = false;

        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return true;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("admin")){
                if (cookie.getValue().equals("Thisisadmin")) {
                    isAdmin = true;
                }
            }
        }
        return !isAdmin;
    }
    public static Optional<User> loggedUser(HttpServletRequest request){
        User  user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return Optional.empty();
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user") && cookie.getValue().equals("Thisisuser")){
                for (Cookie cookie1 : cookies) {
                    if (cookie1.getName().equals("userId")){
                        String value = cookie1.getValue();
                        Optional<User> first = DB.USERS.stream().filter(user1 -> user1.getId().equals(Integer.parseInt(value))).findFirst();
                        if (first.isEmpty()){
                            return Optional.empty();
                        }else {
                            user = first.get();
                        }
                    }
                }
            }
        }
        if (user == null){
            return Optional.empty();
        }else {
            return Optional.of(user);
        }
    }
}
