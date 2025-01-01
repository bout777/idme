package com.idme.mapper;

import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.EngineeringStage;
import com.idme.pojo.entity.Product;
import com.idme.pojo.relation.ProductBlueprintLink;
import com.idme.server.mapper.ProductBlueprintLinkMapper;
import com.idme.server.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductMapperTest {
    @Autowired
    ProductMapper mapper;
    @Autowired
    ProductBlueprintLinkMapper pbMapper;

    @Test
    void testInsert() {
        Product product = Product.builder().productName("testpro")
                .productStage(EngineeringStage.InitialStage)
                .build();
        mapper.insert(product);
    }

    @Test
    void testGetById() {
        Product product = mapper.getById(0L);
        System.out.println(product);
    }

    @Test
    void testLink() {
        pbMapper.insert(0L, 715918127304605696L);
    }

    @Test
    void testLinkget() {
        List<ProductBlueprintLink> list = pbMapper.getByProductId(0L);
        System.out.println(list);
    }

    @Test
    void testLinkdel() {
        pbMapper.deleteByProductId(0L);
    }
}
