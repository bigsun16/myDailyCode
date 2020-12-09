package com.qihui.sun.security.springMvc.service;

import com.qihui.sun.security.springMvc.model.AuthenticationRequest;
import com.qihui.sun.security.springMvc.model.User;

public interface AuthenticationService {

    User authentication(AuthenticationRequest request);
}
