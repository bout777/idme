package com.idme.server.service;

import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
import com.idme.pojo.dto.UserLoginDTO;
import com.idme.pojo.vo.UserLoginVO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserViewDTO userLogin(UserLoginDTO loginForm) {
        return null;
    }

    public void register(UserLoginDTO registerForm) {
        System.out.println("register");
    }
}
