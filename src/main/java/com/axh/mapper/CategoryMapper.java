package com.axh.mapper;

import com.axh.entity.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    int checkCategoryByID(Integer CategoryId);

    int checkCategoryByName(String CategoryName);

    List<Category> selcectCategoryByCategoryId(Integer CategoryId);
}