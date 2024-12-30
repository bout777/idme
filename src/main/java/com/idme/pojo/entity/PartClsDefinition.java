package com.idme.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartClsDefinition {
    private Long id;
    private String name;
    private String description;
    private List<PartAttrDefinition> attrDefs;
}
