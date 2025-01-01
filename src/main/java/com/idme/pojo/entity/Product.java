package com.idme.pojo.entity;

import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.EngineeringStage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    Long id;
    String productOwner;
    String productInformation;
    String productName;
    EngineeringStage productStage;
}
