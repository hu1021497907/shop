package com.axh.controller;

import com.axh.common.ServerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Test {
    @Autowired


    @RequestMapping("/test")
    public ServerResponse test() {
        //插件  分页插件   @RequestParam(defaultValue = "1") Integer id
//        PageHelper.startPage(id, 1);
        return  ServerResponse.success();
    }
}
