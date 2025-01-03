package com.idme.server.controller;

import com.huawei.innovation.rdm.delegate.service.FileDelegatorService;
import com.huawei.innovation.rdm.delegate.service.XdmTokenService;
import com.idme.common.result.PageResult;
import com.idme.common.result.Result;
import com.idme.pojo.dto.DownloadQueryDTO;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.DesignBlueprint;
import com.idme.server.service.DesignBlueprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/blueprint")
public class DesignBlueprintController {
    @Value("${delegate.endpoint}")
    private String endpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private XdmTokenService xdmTokenService;

    @Autowired
    private FileDelegatorService fileDelegatorService;

    @Autowired
    private DesignBlueprintService designBlueprintService;

    @Value("${delegate.subAppId}")
    String appId;

    @PostMapping("/download")
    public void downloadFile(@RequestBody DownloadQueryDTO downloadQuery, HttpServletResponse response) {
        fileDelegatorService.downloadFile(downloadQuery.getFileId(), "DesignBlueprint", "bluePrint", downloadQuery.getDesignBlueprintId(), appId, "0", response);
    }

    @PostMapping("/upload")
    public Result<Long> uploadFile(MultipartFile file, String designBlueprintId) {
        DesignBlueprint.BluePrint bluePrint = designBlueprintService.uploadBluePrint(file, designBlueprintId);
        return Result.success(bluePrint.getId());
    }

    @PostMapping("/deleteFile")
    public Result<Long> deleteFile(Long id) {
        designBlueprintService.deleteFile(id);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result<Long> delete(@RequestParam Long id) {
        designBlueprintService.delete(id);
        return Result.success();
    }

    @PostMapping("/insert")
    public Result<Long> insert(@RequestBody DesignBlueprint designBlueprint) {
        Long id = designBlueprintService.insert(designBlueprint);
        return Result.success(id);
    }

    @PostMapping("/update")
    public Result<Long> update(@RequestBody DesignBlueprint designBlueprint) {
        designBlueprintService.update(designBlueprint);
        return Result.success();
    }

    @PostMapping("/page")
    public Result<PageResult> page(@RequestBody SearchQueryDTO query) {
        PageResult pageResult = designBlueprintService.page(query);
        return Result.success(pageResult);
    }

    @GetMapping("/getByProductId")
    public Result<List<DesignBlueprint>> getByProductId(Long id) {
        List<DesignBlueprint> res = designBlueprintService.getByProductId(id);
        return Result.success(res);
    }

}
