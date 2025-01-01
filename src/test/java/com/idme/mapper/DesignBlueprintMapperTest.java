package com.idme.mapper;

import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.DesignBlueprint;
import com.idme.server.mapper.DesignBlueprintMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DesignBlueprintMapperTest {
    @Autowired
    DesignBlueprintMapper mapper;

    @Test
    void testInsert() {
        DesignBlueprint designBlueprint = DesignBlueprint.builder()
                .blueprintDescription("testprint1")
                .bluePrint(new ArrayList<>())
                .build();

        mapper.insert(designBlueprint);
    }

    @Test
    void testGetById() {
        DesignBlueprint res = mapper.getById(715916857378394112L);
        System.out.println(res);
    }

    @Test
    void testDelete() {
        mapper.delete(715916857378394112L);
    }

    @Test
    void testUpdate() {
        DesignBlueprint designBlueprint = mapper.getById(715918127304605696L);
        mapper.update(designBlueprint);
    }

    @Test
    void testPage() {
        List<DesignBlueprint> list = mapper.pageDesignBlueprints(SearchQueryDTO.builder().page(1).pageSize(10).build());
        System.out.println(list);
    }
}
