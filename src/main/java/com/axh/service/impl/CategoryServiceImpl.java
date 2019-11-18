package com.axh.service.impl;

import com.axh.common.ResponseCodeEnum;
import com.axh.common.ServerResponse;
import com.axh.entity.Category;
import com.axh.mapper.CategoryMapper;
import com.axh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse getCategory(Integer CategoryId) {
//        判断是否存在
        if (!checkCategoryByID(CategoryId)) {
            return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(), "id不存在");
        }

        return ServerResponse.success(categoryMapper.selcectCategoryByCategoryId(CategoryId));
    }

    @Override
    public ServerResponse addCategory(Integer parenId, String categoryName) {
        int resultCount = categoryMapper.checkCategoryByName(categoryName);
        if (resultCount != 0) {
            return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(), "类别已存在");
        }
        if (!parenId.equals(0)&&!checkCategoryByID(parenId)) {
            return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(), "父类名不存在");
        }
        Category category = new Category();
        category.setParentId(parenId);
        category.setName(categoryName);
        category.setStatus(true);
        int row = categoryMapper.insertSelective(category);
        if (row == 0) {
            return ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(), "增加失败");
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse updateCategory(Integer CategoryId, String categoryName) {
        if (!checkCategoryByID(CategoryId)) {
            return  ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(),"类别不存在");
        }
        Category category=new Category();
        category.setName(categoryName);
        category.setId(CategoryId);
        int res=categoryMapper.updateByPrimaryKeySelective(category);
        if (res==0) {
            return  ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(),"修改失败");
        }
        return ServerResponse.success();
    }

    /**
     *存在返回true
     * @param CategoryId
     * @return
     */
    private boolean checkCategoryByID(Integer CategoryId){
        if(categoryMapper.checkCategoryByID(CategoryId) == 0){
            return false;
        }
        return true;
    }
}
