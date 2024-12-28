/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2024-2024. All rights reserved.
 */

package com.idme.server.controller;

import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.UserDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
import com.idme.common.properties.JwtProperties;
import com.idme.common.result.Result;
import com.idme.common.utils.JwtUtil;
import com.idme.pojo.dto.UserLoginDTO;
import com.idme.pojo.vo.UserLoginVO;
import com.idme.server.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * DelegatorController
 *
 * @since 2024-04-11
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtProperties jwtProperties;
    Result<UserLoginVO> login(@RequestBody UserLoginDTO loginForm){
        UserViewDTO user = userService.userLogin(loginForm);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        UserLoginVO loginVO = UserLoginVO.builder()
                .token(token)
                .id(user.getId())
                .authority(user.getAuthority())
                .build();
        return Result.success(loginVO);
    };

    Result register(@RequestBody UserLoginDTO registerForm){
        userService.register(registerForm);
        return Result.success();
    }


}

