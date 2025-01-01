package com.idme.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DownloadQueryDTO implements Serializable {
    String  fileId;
    String  DesignBlueprintId;
}
