/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2024-2024. All rights reserved.
 */

package com.idme.server.controller;

import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
import com.idme.common.constant.JwtConstant;
import com.idme.common.properties.JwtProperties;
import com.idme.common.result.PageResult;
import com.idme.common.result.Result;
import com.idme.common.utils.JwtUtil;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.dto.UserFormDTO;
import com.idme.pojo.entity.User;
import com.idme.pojo.vo.UserLoginVO;
import com.idme.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    Result<UserLoginVO> login(@RequestBody UserFormDTO loginForm) {
        User user = userService.userLogin(loginForm);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtConstant.USER_ID, user.getId());
        claims.put(JwtConstant.USER_AUTHORITY, user.getAuthority());
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        UserLoginVO loginVO = UserLoginVO.builder()
                .token(token)
                .id(user.getId())
                .authority(user.getAuthority())
                .build();
        return Result.success(loginVO);
    }

    ;

    @PostMapping("/insert")
    public void insert(@RequestBody User user) {
        userService.insert(user);
    }

    @PostMapping("/page")
    public Result<PageResult> page(@RequestBody SearchQueryDTO query) {
        PageResult pageResult = userService.pageUser(query);
        return Result.success(pageResult);
    }

    @GetMapping("/getById/{id}")
    public Result<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

}

