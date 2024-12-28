package com.idme.server.mapper;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.UserDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
import com.idme.pojo.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
//    void insert(UserViewDTO);
    @Autowired
    private UserDelegator userDelegator;
    public User getById(Long id){
        PersistObjectIdDecryptDTO persistObjectIdDecryptDTO = new PersistObjectIdDecryptDTO();
        persistObjectIdDecryptDTO.setId(id);
        UserViewDTO userView = userDelegator.get(persistObjectIdDecryptDTO);
        User user = new User();
        BeanUtils.copyProperties(userView, user);
        return user;
    }

    public User getByName(String name){
        QueryRequestVo query = QueryRequestVo.build();
        query.addCondition("name", ConditionType.EQUAL, name);
        List<UserViewDTO> res = userDelegator.find(query, new RDMPageVO(1, 10));
        User user = new User();
        BeanUtils.copyProperties(res.get(0), user);
        return user;
    }
}
