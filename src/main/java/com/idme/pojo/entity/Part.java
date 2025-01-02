package com.idme.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Part implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String partName;
    private String cls;
    private List<PartAttr> attrs;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PartAttr {
        private String key;
        private String value;
    }

}
