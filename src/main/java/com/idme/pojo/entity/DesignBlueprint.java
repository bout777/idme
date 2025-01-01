package com.idme.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesignBlueprint implements Serializable {
    private Long id;

    private String blueprintDescription;
    private List<BluePrint> bluePrint;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BluePrint implements Serializable {
        private Long id;
        private String name;
    }
}
