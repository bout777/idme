package com.idme.pojo.entity;

import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    Long id;
    Authority authority;
    String name;
    String user_password;
    String phone;
}
