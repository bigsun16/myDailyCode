package com.qihui.sun.security.springMvc.controller;

import com.qihui.sun.security.springMvc.model.AuthenticationRequest;
import com.qihui.sun.security.springMvc.model.User;
import com.qihui.sun.security.springMvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login",
            produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest request){
        User authentication = authenticationService.authentication(request);
        return authentication.getFullName() + "登录成功";
    }
}
