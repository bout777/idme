package com.idme.server.controller;

import com.huawei.iit.sdk.common.vo.file.UploadFileModelVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.delegate.service.FileDelegatorService;
import com.huawei.innovation.rdm.delegate.service.XdmTokenService;
import com.idme.common.result.Result;
import com.idme.pojo.dto.DownloadQueryDTO;
import com.idme.pojo.entity.DesignBlueprint;
import com.idme.server.service.DesignBlueprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@RestController
@RequestMapping("common")
public class CommonController {
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
        fileDelegatorService.downloadFile(downloadQuery.getFileId(),"DesignBlueprint","bluePrint",downloadQuery.getDesignBlueprintId(),appId,"0",response);
    }

    @PostMapping("/upload")
    public Result<DesignBlueprint.BluePrint> uploadFile(MultipartFile file,String DesignBlueprintId){
        DesignBlueprint.BluePrint bluePrint = designBlueprintService.uploadBluePrint(file, DesignBlueprintId);
        return Result.success(bluePrint);
    }
}
