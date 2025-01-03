package com.idme.pojo.entity;

import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    Long id;
    Authority authority;
    String name;
    String user_Password;
    String phone;
}
