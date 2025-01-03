package com.idme;

import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.UserDelegator;
import com.huawei.innovation.rdm.xdm.delegator.ClassificationNodeDelegator;
import com.huawei.innovation.rdm.xdm.delegator.ClassificationNodeRelatedByTypeDefinitionDelegator;
import com.huawei.innovation.rdm.xdm.delegator.EXADefinitionDelegator;
import com.huawei.innovation.rdm.xdm.delegator.EXADefinitionLinkDelegator;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.User;
import com.idme.server.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
