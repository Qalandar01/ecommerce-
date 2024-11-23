package org.example.demo4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    private Integer id = idGen++;
    private String name;
    private String phone;
    private String password;
    private static Integer idGen = 1;
    public static Integer catId = 0;

    public User(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
    }
}
