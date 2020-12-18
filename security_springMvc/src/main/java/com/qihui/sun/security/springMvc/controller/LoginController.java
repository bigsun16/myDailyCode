package com.qihui.sun.security.springMvc.controller;

import com.qihui.sun.security.springMvc.model.AuthenticationRequest;
import com.qihui.sun.security.springMvc.model.User;
import com.qihui.sun.security.springMvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login",
            produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest request, HttpSession session){
        User authentication = authenticationService.authentication(request);
        session.setAttribute(User.SESSION_USER_KEY,authentication);
        return authentication.getFullName() + "登录成功了";
    }

    @GetMapping(value = "/r/r1",
            produces = "text/plain;charset=utf-8")
    public String resource1(HttpSession session){
        Object user = session.getAttribute(User.SESSION_USER_KEY);
        if (user==null){
            return "匿名访问资源1";
        } else {
            return "欢迎"+((User)user).getFullName()+"访问资源1";
        }
    }
    @GetMapping(value = "/r/r2",
            produces = "text/plain;charset=utf-8")
    public String resource2(HttpSession session){
        Object user = session.getAttribute(User.SESSION_USER_KEY);
        if (user==null){
            return "匿名访问资源2";
        } else {
            return "欢迎"+((User)user).getFullName()+"访问资源2";
        }
    }

    @RequestMapping(value = "/logout",
            produces = "text/plain;charset=utf-8")
    public String logout(HttpSession session){
        Object user = session.getAttribute(User.SESSION_USER_KEY);
        if (user==null){
            return "还未登录";
        } else {
            session.removeAttribute(User.SESSION_USER_KEY);
            return "登出成功";
        }
    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
