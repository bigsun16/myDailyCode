package com.qihui.sun.security.springMvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    public static String SESSION_USER_KEY = "_user";
    private String id;
    private String userName;
    private String password;
    private String fullName;
    private String mobile;
    private Set<String> authorities;
}
