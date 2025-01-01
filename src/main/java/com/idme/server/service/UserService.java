package com.idme.server.service;

import com.idme.common.constant.MessageConstant;
import com.idme.common.exception.BaseException;
import com.idme.common.result.PageResult;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.dto.UserFormDTO;
import com.idme.pojo.entity.User;
import com.idme.server.annotation.Admin;
import com.idme.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User userLogin(UserFormDTO loginForm) {
        User user = userMapper.getByName(loginForm.getUsername());
        if (user == null)
            throw new BaseException(MessageConstant.NAME_NOT_EXIST);
        if (!user.getUser_Password().equals(loginForm.getPassword()))
            throw new BaseException(MessageConstant.WRONG_PASSWORD);

        return user;
    }

    @Admin
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Admin
    public void insert(User userVO) {
        User user = userMapper.getByName(userVO.getName());
        if (user != null)
            throw new BaseException(MessageConstant.USER_ALREADY_EXIST);

        userMapper.insert(userVO);
    }

    @Admin
    public void update(User user) {
        userMapper.update(user);
    }

    @Admin
    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Admin
    public PageResult pageUser(SearchQueryDTO query) {
        List<User> records = userMapper.pageUser(query);
        Long total = userMapper.count(query);
        return PageResult.builder()
                .records(records)
                .total(total)
                .build();
    }


}
