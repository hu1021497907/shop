package com.axh.service;

import com.axh.common.ServerResponse;

public interface ProductService {
    ServerResponse list(Integer pageSize, Integer pageNum);

    ServerResponse getDetail(Integer productId);
}
