package com.idme.pojo.entity;

import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.SourceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    Long id;
    //对应产品名称
    private String name;
    //产品数量
    private String quantity;
    private Timestamp orderDate;
    private SourceType type;
}
