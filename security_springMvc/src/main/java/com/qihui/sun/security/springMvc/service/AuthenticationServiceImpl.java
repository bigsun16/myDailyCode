package com.qihui.sun.security.springMvc.service;

import com.qihui.sun.security.springMvc.model.AuthenticationRequest;
import com.qihui.sun.security.springMvc.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Override
    public User authentication(AuthenticationRequest request) {
        if (request==null || StringUtils.isEmpty(request.getUserName()) || StringUtils.isEmpty(request.getPassword())){
            throw new RuntimeException("账号或密码为空了");
        }
        User userByName = getUserByName(request.getUserName());
        if (userByName==null){
            throw new RuntimeException("账号不存在");
        }

        if (!userByName.getPassword().equals(request.getPassword())){
            throw new RuntimeException("密码错误");
        }
        return userByName;
    }

    private User getUserByName(String name){
        return userMap.get(name);
    }

    private Map<String,User> userMap = new HashMap<String,User>();
    {
        Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1");
        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p2");
        userMap.put("zhang san",new User("1","zhang san","123","张三","123456",authorities1));
        userMap.put("li si",new User("2","li si","123","李四","1234567",authorities2));
    }

}
