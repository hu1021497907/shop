package com.axh.service.impl;

import com.axh.common.Constant;
import com.axh.common.ResponseCodeEnum;
import com.axh.common.ServerResponse;
import com.axh.entity.User;
import com.axh.mapper.UserMapper;
import com.axh.service.UserService;
import com.axh.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse login(String username, String password) {
        // service不允许调用request
        //获取数据库中username
        User user = userMapper.getUserByUsername(username);
        // 校验用户名是否存在
        if (user == null) {
            return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(), "用户名不存在");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        // 密码是否正确
        if (!user.getPassword().equals(md5Password)) {
            return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(), "密码错误");
        }
        // 返回结果
        // todo  后续替换成工具类控制
        user.setPassword(null);
        return ServerResponse.success("登录成功", user);
    }

    @Override
    public ServerResponse checkUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            return ServerResponse.success("用户名可用");
        }
        return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(), "用户已存在");
    }

    @Override
    public ServerResponse checkEmail(String email) {
        int resultCount = userMapper.checkEmail(email);
        if (resultCount == 0) {
            return ServerResponse.success("邮箱可用");
        }
        return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(), "邮箱已存在");

    }

    @Override
    public ServerResponse register(User user) {
//        判断不合法
        if(!checkUsername(user.getUsername()).isSuccess()){
            return  ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(),"用户名已存在");
        }
        if(!checkEmail(user.getEmail()).isSuccess()){
            return  ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(),"邮箱已存在");
        }
//        设置为普通用户
        user.setRole(Constant.Role.ORDINARY_USER);
//        为密码加密
        String md5password=MD5Util.MD5EncodeUtf8(user.getPassword());
        user.setPassword(md5password);
//        调用Mapper插入数据
       int count= userMapper.insertSelective(user);
       if (count==0){
           return  ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(),"插入失败");
       }
        return ServerResponse.success("成功");
    }
}
