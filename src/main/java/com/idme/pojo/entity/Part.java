package com.idme.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Part implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String partName;
    private PartCls Cls;
    @Data
    public static class PartCls {
        private String name;
        private List<PartAttr> attrs;
        @Data
        public static class PartAttr {
            private String name;
            private String value;
        }
    }

}
