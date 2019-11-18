package com.axh.controller;

import com.axh.common.ResponseCodeEnum;
import com.axh.common.ServerResponse;
import com.axh.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("get_category")
    public ServerResponse getCategory(@RequestParam(defaultValue = "0") Integer categoryId){
        return categoryService.getCategory(categoryId);
    }
    @PostMapping("add_category")
    public ServerResponse addCategory(@RequestParam(defaultValue = "0")Integer parenId, String categoryName ){
        //  判断name是否为空  防止空指针
        if (StringUtils.isAnyBlank(categoryName)) {
            return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(),"类名不能为空");
        }
        return categoryService.addCategory(parenId,categoryName);
    }
    @PostMapping("set_category_name")
    public  ServerResponse setCategoryName(Integer categoryId,String categoryName ){
        //todo  校验两个参数合法性
        if(categoryId==null){
            return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(),"id不能为空");
        }
        if (StringUtils.isBlank(categoryName)) {
            return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(),"类名不能为空");
        }
        return categoryService.updateCategory(categoryId,categoryName);
    }
}
