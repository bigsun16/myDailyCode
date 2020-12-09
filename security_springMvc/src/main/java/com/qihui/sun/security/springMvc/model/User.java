package com.qihui.sun.security.springMvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String id;
    private String userName;
    private String password;
    private String fullName;
    private String mobile;
}
