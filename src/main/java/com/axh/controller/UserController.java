package com.axh.controller;

import com.axh.common.Constant;
import com.axh.common.ResponseCodeEnum;
import com.axh.common.ServerResponse;
import com.axh.entity.User;
import com.axh.form.UserForm;
import com.axh.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    //登录页面
    @PostMapping("login")
    public ServerResponse login(String username, String password, HttpSession httpSession) {
        ServerResponse serverResponse = userService.login(username, password);
        if (serverResponse.isSuccess()) {
            httpSession.setAttribute(Constant.CURRENT_USER, serverResponse.getData());
        }
        return serverResponse;
    }

    //    退出
    @PostMapping("logout")
    public ServerResponse logout(HttpSession httpSession) {
        httpSession.removeAttribute(Constant.CURRENT_USER);
        return ServerResponse.success("退出成功");
    }

    @PostMapping("get_user_info")
    public ServerResponse getUserInfo(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Constant.CURRENT_USER);
        if (user == null) {
            return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(), "用户未登录，无法获取信息");
        }
        return ServerResponse.success(user);
    }
//    检查用户名，邮箱是否可用
    @PostMapping("check_valid")
    public  ServerResponse checkValid(String str,String type){
        if(Constant.USERNAME.equals(type)){
            return  userService.checkUsername(str);
        }
        if(Constant.EMAIL.equals(type)){
            return userService.checkEmail(str) ;
        }
        return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(),"参数错误" );
    }
//    注册
    @PostMapping("register")
    public ServerResponse register(@Valid UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
//         for (ObjectError error : bindingResult.getFieldErrors()) {
//                 System.out.println(error.getDefaultMessage());
//         }
            return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(),bindingResult.getFieldError().getDefaultMessage());
     }

        //todo  校验格式合法性
        User user=new User();
        BeanUtils.copyProperties(userForm,user);
//        return userService.register();
        return  userService.register(user);
    }

}
