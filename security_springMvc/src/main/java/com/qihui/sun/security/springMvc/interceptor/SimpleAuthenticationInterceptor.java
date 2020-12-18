package com.qihui.sun.security.springMvc.interceptor;

import com.qihui.sun.security.springMvc.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object object = request.getSession().getAttribute(User.SESSION_USER_KEY);
        if (object==null){
            writeContent(response,"请登录");
            return false;
        }
        User user = (User) object;
        String requestURI = request.getRequestURI();
        if (requestURI.contains("r/r1") && user.getAuthorities().contains("p1")){
            return true;
        }
        if (requestURI.contains("r/r2") && user.getAuthorities().contains("p2")){
            return true;
        }
        writeContent(response,"没有权限访问该资源");
        return false;
    }

    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
    }
}
