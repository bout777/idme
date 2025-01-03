package com.idme;

import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.xdm.delegator.JsonAttributeLayoutDelegator;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.PartClsDefinition;
import com.idme.server.mapper.PartClsMapper;
import com.idme.server.mapper.PartMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void partMapperTest2() {
        SearchQueryDTO q = new SearchQueryDTO();
        q.setPage(1);
        q.setPageSize(10);
        partMapper.pagePart(q);
    }

    @Test
    void partMapperTestfuck() {
        long count = delegator.count(new QueryRequestVo());
        System.out.println(count);
    }
}
