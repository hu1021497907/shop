package com.axh.controller;

import com.axh.common.ServerResponse;
import com.axh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public ServerResponse list(@RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "1") Integer pageNum){

        return productService.list(pageSize,pageNum);
    }
    @GetMapping("detail")
    public ServerResponse detail(Integer productId){

        return productService.getDetail(productId);
    }
}
