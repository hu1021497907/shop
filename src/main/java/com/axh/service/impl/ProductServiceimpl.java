package com.axh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.axh.common.Constant;
import com.axh.common.ResponseCodeEnum;
import com.axh.common.ServerResponse;
import com.axh.entity.Product;
import com.axh.mapper.ProductMapper;
import com.axh.service.ProductService;
import com.axh.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceimpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public ServerResponse list(Integer pageSize, Integer pageNum) {
        //在查询之前配置分页参数
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        //调用查询
        List<Product> productList=productMapper.list(Constant.ProductStatus.NOW);
        // 转换成vo对象
        List<ProductVO> productVOS=new ArrayList<>();
        for (Product product : productList) {
            ProductVO productVO=new ProductVO();
            BeanUtils.copyProperties(product,productVO);
            productVOS.add(productVO);

        }
        //使用pagehelper提供的类进行信息封装
        PageInfo<ProductVO> pageInfo=new PageInfo<>(productVOS);
        return ServerResponse.success(pageInfo);
    }

    @Override
    public ServerResponse getDetail(Integer productId) {
        Product product=productMapper.selectByPrimaryKey(productId);
        if(product==null){
            return  ServerResponse.error(ResponseCodeEnum.ERROR.getStatus(),"商品不存在");
        }

        return ServerResponse.success(product);
    }
}
