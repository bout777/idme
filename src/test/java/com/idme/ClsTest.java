package com.idme;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.xdm.delegator.JsonAttributeLayoutDelegator;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Part;
import com.idme.pojo.entity.PartClsDefinition;
import com.idme.server.mapper.PartClsMapper;
import com.idme.server.mapper.PartMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ClsTest {
    @Autowired
    private PartClsMapper mapper;
    @Autowired
    private PartMapper partMapper;
    @Autowired
    JsonAttributeLayoutDelegator delegator;

    @Test
    void t() {
        List<PartClsDefinition> defs = mapper.getAllClsDefs();
        System.out.println(defs);

    }

    @Test
    void partMapperTest() {
        Part p = new Part();
        p.setName("test3");
        List<Part.PartCls.PartAttr> attrs = new ArrayList<>();
        Part.PartCls cls = new Part.PartCls();

        Part.PartCls.PartAttr attr = new Part.PartCls.PartAttr();

        attr.setName("length");
        attr.setValue("10");
        attrs.add(attr);
        attr = new Part.PartCls.PartAttr();
        attr.setName("width");
        attr.setValue("10");

        attrs.add(attr);
        cls.setAttrs(attrs);
        p.setCls(cls);

        partMapper.insert(p);
    }

    @Test
    void partMapperTest2() {
        SearchQueryDTO q = new SearchQueryDTO();
        q.setPage(1);
        q.setPageSize(10);
        partMapper.pagePart(q);
    }

    @Test
    void partMapperTestUpdate() throws JsonProcessingException {
        Part p = new Part();
        p.setId(715311166712250368L);
        p.setName("test3");
        List<Part.PartCls.PartAttr> attrs = new ArrayList<>();
        Part.PartCls cls = new Part.PartCls();

        Part.PartCls.PartAttr attr = new Part.PartCls.PartAttr();

        attr.setName("length");
        attr.setValue("10");
        attrs.add(attr);
        attr = new Part.PartCls.PartAttr();
        attr.setName("width");
        attr.setValue("10");

        attrs.add(attr);
        cls.setAttrs(attrs);
        p.setCls(cls);
        partMapper.update(p);
    }

    @Test
    void partMapperTestfuck() {
        long count = delegator.count(new QueryRequestVo());
        System.out.println(count);
    }
}
