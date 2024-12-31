package com.idme.pojo.dto;

import lombok.Data;

@Data
public class SearchQueryDTO {
    private Long id;
    private String name;
    private Integer page;
    private Integer pageSize;
}
