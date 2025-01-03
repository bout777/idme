package com.idme.pojo.vo;

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
public class UserLoginVO implements Serializable {
    private Long id;
    private String token;
    private Authority authority;
}
