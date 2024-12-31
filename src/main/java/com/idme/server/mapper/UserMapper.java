package com.idme.server.mapper;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.UserDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
import com.idme.common.utils.CommonUtil;
import com.idme.pojo.dto.SearchQueryDTO;
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
        User user = CommonUtil.resConvert(res.get(0), User.class);
        return user;
    }

    public List<User> pageUser(SearchQueryDTO query){
        QueryRequestVo q = CommonUtil.queryConvert(query);
        RDMPageVO p = CommonUtil.pageConvert(query);

        List<UserViewDTO> list = userDelegator.find(q, p);

        List<User> res = CommonUtil.ListResConvert(list, User.class);
        return res;
    }

    public void insert(User user){
        UserCreateDTO u = new UserCreateDTO();
        BeanUtils.copyProperties(user, u);
        userDelegator.create(u);
    }

    public void update(User user){
        UserUpdateDTO u = new UserUpdateDTO();
        BeanUtils.copyProperties(user, u);
        userDelegator.update(u);
    }

    public void delete(Long id){
        PersistObjectIdModifierDTO obj = new PersistObjectIdModifierDTO(id,"");
        userDelegator.delete(obj);
    }
}
