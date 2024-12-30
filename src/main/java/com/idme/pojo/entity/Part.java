package com.idme.pojo.entity;

import java.util.List;


public class Part {
    private Long id;
    private String name;
    private String description;
    private String partName;
    private PartCls Cls;

    public static class PartCls {
        private String name;
        private List<PartAttr> attrs;

        public static class PartAttr {
            private String name;
            private String value;
        }
    }

}
