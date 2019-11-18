package com.axh.service;

import com.axh.common.ServerResponse;
import com.axh.entity.User;

public interface UserService {
    ServerResponse login(String username,String password);
    ServerResponse checkUsername(String username);
    ServerResponse checkEmail(String email);
    ServerResponse register(User user);
}
