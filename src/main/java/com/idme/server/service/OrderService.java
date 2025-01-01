package com.idme.server.service;

import com.idme.common.exception.BaseException;
import com.idme.common.constant.MessageConstant;
import com.idme.common.result.PageResult;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Order;
import com.idme.pojo.entity.Product;
import com.idme.server.mapper.OrderMapper;
import com.idme.server.mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ProductMapper productMapper;

    public void insert(Order order) {
        String ProductName = order.getName();
        Product product = productMapper.getByName(ProductName);
        if(product == null)
            throw new BaseException(MessageConstant.PRODUCT_NOT_EXIST);
        orderMapper.insert(order);
    }

    public void update(Order order) {
        String ProductName = order.getName();
        Product product = productMapper.getByName(ProductName);
        if(product == null)
            throw new BaseException(MessageConstant.PRODUCT_NOT_EXIST);
        orderMapper.update(order);
    }

    public void delete(Long id) {
        orderMapper.delete(id);
    }

    public Order getById(Long id) {
        return orderMapper.getById(id);
    }

    public PageResult page(SearchQueryDTO query) {
        List<Order> records = orderMapper.pageOrder(query);
        Long total = orderMapper.count(query);
        return PageResult.builder()
                .records(records)
                .total(total)
                .build();
    }
}
