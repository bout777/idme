package com.idme.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserFormDTO implements Serializable {
    private String username;
    private String password;
}
