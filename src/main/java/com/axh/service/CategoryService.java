package com.axh.service;

import com.axh.common.ServerResponse;

public interface CategoryService {

    ServerResponse getCategory(Integer CategoryId);

    ServerResponse addCategory(Integer parenId,String categoryName);

    ServerResponse updateCategory(Integer CategoryId,String categoryName);

}
