package com.idme;

import com.huawei.innovation.rdm.bean.entity.ClassificationEXAValue;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdsDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.Authority;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserCreateDTO;
import com.huawei.innovation.rdm.xdm.delegator.*;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeViewDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionListViewDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;
import com.huawei.innovation.rdm.xdm.dto.relation.EXADefinitionLinkListViewDTO;
import com.huawei.innovation.rdm.xdm.dto.relation.EXADefinitionLinkViewDTO;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.server.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.UserDelegator;
import com.idme.pojo.entity.User;

import java.util.List;

@SpringBootTest
class IdmeApplicationTests {

    @Autowired
    private UserDelegator userDelegator;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClassificationNodeDelegator classificationNodeDelegator;
    @Autowired
    private ClassificationNodeRelatedByTypeDefinitionDelegator classificationNodeRelatedByTypeDefinitionDelegator;
    @Autowired
    private EXADefinitionDelegator exaDefinitionDelegator;
    @Autowired
    private EXADefinitionLinkDelegator exaDefinitionLinkDelegator;

    @Test
    void userInsertTest() {
        User user = User.builder().name("testUser5").build();
        userMapper.insert(user);
    }

    @Test
    void userGetTest() {
        System.out.println(userMapper.getByName("test"));
    }

    @Test
    void UserPageTest() {
        List<User> users = userMapper.pageUser(SearchQueryDTO.builder().page(1).pageSize(10).build());
        System.out.println(users);
    }

}
