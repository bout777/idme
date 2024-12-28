package com.idme;

import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.Authority;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.UserDelegator;

import java.util.List;

@SpringBootTest
class IdmeApplicationTests {

    @Autowired
    private UserDelegator userDelegator;
    @Test
    void contextLoads() {
        UserCreateDTO  userCreateDTO = new UserCreateDTO();
        userCreateDTO.setName("test");
        userCreateDTO.setAuthority(Authority.Normal);
        userDelegator.create(userCreateDTO);
    }

    @Test
    void contextLoads2() {
        QueryRequestVo queryRequestVo = new QueryRequestVo();

        List<UserViewDTO>  res= userDelegator.find(queryRequestVo,new RDMPageVO(1,10));
        System.out.println(res);
    }

}
