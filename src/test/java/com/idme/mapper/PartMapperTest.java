package com.idme.mapper;

import com.idme.pojo.entity.Part;
import com.idme.server.mapper.PartMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PartMapperTest {
    @Autowired
    private PartMapper partMapper;
    @Test
    void test() {
        List<Part.PartAttr> list = new ArrayList<>();
        list.add(Part.PartAttr.builder().key("key1").value("1").build());
        list.add(Part.PartAttr.builder().key("key2").value("2").build());
        list.add(Part.PartAttr.builder().key("key3").value("3").build());
        String s = partMapper.attrsConvert(list);
        System.out.println(s);

    }

    @Test
    void test2() {
        List<Part.PartAttr> list = partMapper.attrsConvert("[{\"key\":\"key1\",\"value\":\"1\"},{\"key\":\"key2\",\"value\":\"2\"},{\"key\":\"key3\",\"value\":\"3\"}]");
        System.out.println(list);
    }
}
