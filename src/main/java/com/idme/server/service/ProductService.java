package com.idme.server.service;

import com.idme.common.result.PageResult;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Product;
import com.idme.server.mapper.ProductBlueprintLinkMapper;
import com.idme.server.mapper.ProductMapper;
import com.idme.server.mapper.ProductPartLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductBlueprintLinkMapper productBlueprintLinkMapper;
    @Autowired
    private ProductPartLinkMapper productPartLinkMapper;

    public void insert(Product product) {
        productMapper.insert(product);
    }

    public void update(Product product) {
        productMapper.update(product);
    }

    public Product getById(Long id) {
        return productMapper.getById(id);
    }

    public void delete(Long id) {
        productMapper.deleteById(id);
        productBlueprintLinkMapper.deleteByProductId(id);
        productPartLinkMapper.deleteByProductId(id);
    }

    public PageResult page(SearchQueryDTO query) {
        List<Product> record = productMapper.pageProduct(query);
        Long total = productMapper.count(query);
        return PageResult.builder()
                .total(total)
                .records(record)
                .build();
    }
}
