package com.idme.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartAttrDefinition {
    private Long id;
    private String name;
    private String enName;
    private String description;
    private String type;
}
