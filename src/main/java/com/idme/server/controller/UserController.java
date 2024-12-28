/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2024-2024. All rights reserved.
 */

package com.idme.server.controller;

import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.UserDelegator;
import com.idme.common.result.Result;
import com.idme.common.utils.JwtUtil;
import com.idme.pojo.dto.UserLoginDTO;
import com.idme.pojo.vo.UserLoginVO;
import com.idme.server.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
//    Result<UserLoginVO> login(@RequestBody UserLoginDTO loginForm){
//        UserLoginVO userLoginVO = userService.userLogin(loginForm);
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("userId", admin.getId());
//        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
//        AdminLoginVO loginVO = AdminLoginVO.builder().token(token).id(admin.getId()).build();
//        return Result.success(loginVO);
//    };
}

