package com.qihui.sun.security.springMvc.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String userName;
    private String password;
}
