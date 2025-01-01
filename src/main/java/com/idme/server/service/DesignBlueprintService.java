package com.idme.server.service;

import com.huawei.iit.sdk.common.vo.file.UploadFileModelVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.delegate.service.FileDelegatorService;
import com.idme.common.result.Result;
import com.idme.pojo.entity.DesignBlueprint;
import com.idme.server.mapper.DesignBlueprintMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class DesignBlueprintService {
    @Autowired
    private  FileDelegatorService fileDelegatorService;
    @Autowired
    private DesignBlueprintMapper designBlueprintMapper;
    public DesignBlueprint.BluePrint uploadBluePrint(MultipartFile file, String DesignBlueprintId){
        UploadFileModelVO uploadFileModelVO = new UploadFileModelVO();

//        String id = UUID.randomUUID().toString();
        uploadFileModelVO.setInstanceId(DesignBlueprintId);
        uploadFileModelVO.setFile(file);
        uploadFileModelVO.setModelNumber("DM02981723");
        uploadFileModelVO.setModelName("DesignBlueprint");
        uploadFileModelVO.setAttributeName("bluePrint");
        uploadFileModelVO.setApplicationId("rdm_2485d6e3cbf74c538c89c46777250db7_app");
        uploadFileModelVO.setUsername("jt");
        uploadFileModelVO.setEncrypted(false);
        uploadFileModelVO.setStorageType(0);
        uploadFileModelVO.setExaAttr("0");

        MultipartFile[] files = new MultipartFile[1];
        files[0] = file;
        uploadFileModelVO.setFiles(files);

        RDMResultVO rdmResultVO = fileDelegatorService.uploadFile(uploadFileModelVO);
        Long fileId = Long.valueOf(rdmResultVO.getData().get(0).toString());

        DesignBlueprint.BluePrint bluePrint = DesignBlueprint.BluePrint.builder()
                .id(fileId)
                .name(file.getOriginalFilename())
                .build();

        DesignBlueprint designBlueprint = designBlueprintMapper.getById(Long.valueOf(DesignBlueprintId));
        if(designBlueprint.getBluePrint()==null)
            designBlueprint.setBluePrint(new ArrayList<>());
        designBlueprint.getBluePrint().add(bluePrint);
        designBlueprintMapper.update(designBlueprint);

        return bluePrint;
    }
}
