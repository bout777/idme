package com.idme.mapper;

import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.Authority;
import com.idme.pojo.entity.User;
import com.idme.server.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper mapper;

    @Test
    void testInsert() {
        User user = User.builder()
                .name("haha")
                .authority(Authority.Admin)
                .user_Password("123456")
                .build();
        mapper.insert(user);
    }

    @Test
    void testGetById() {
        User user = mapper.getById(0L);
        System.out.println(user);
    }
}
