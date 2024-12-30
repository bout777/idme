package com.idme;

import com.idme.pojo.entity.PartClsDefinition;
import com.idme.server.mapper.PartClsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClsTest {
    @Autowired
    private PartClsMapper mapper;
    @Test
    void t(){
        List<PartClsDefinition> defs = mapper.getAllClsDefs();
        System.out.println(defs);

    }
}
