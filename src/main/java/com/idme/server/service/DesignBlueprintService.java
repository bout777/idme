package com.idme.server.service;

import com.huawei.iit.sdk.common.vo.file.UploadFileModelVO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdsModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.delegate.service.FileDelegatorService;
import com.idme.common.constant.MessageConstant;
import com.idme.common.exception.BaseException;
import com.idme.common.result.PageResult;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.DesignBlueprint;
import com.idme.pojo.relation.ProductBlueprintLink;
import com.idme.server.mapper.DesignBlueprintMapper;
import com.idme.server.mapper.ProductBlueprintLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class DesignBlueprintService {
    @Value("${delegate.subAppId}")
    private String appId;
    @Autowired
    private FileDelegatorService fileDelegatorService;
    @Autowired
    private DesignBlueprintMapper designBlueprintMapper;
    @Autowired
    private ProductBlueprintLinkMapper productBlueprintLinkMapper;

    public DesignBlueprint.BluePrint uploadBluePrint(MultipartFile file, String DesignBlueprintId) {
        UploadFileModelVO uploadFileModelVO = new UploadFileModelVO();

        uploadFileModelVO.setInstanceId(DesignBlueprintId);
        uploadFileModelVO.setFile(file);
        uploadFileModelVO.setModelNumber("DM02981723");
        uploadFileModelVO.setModelName("DesignBlueprint");
        uploadFileModelVO.setAttributeName("bluePrint");
        uploadFileModelVO.setApplicationId(appId);
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
        if (designBlueprint.getBluePrint() == null)
            designBlueprint.setBluePrint(new ArrayList<>());
        designBlueprint.getBluePrint().add(bluePrint);
        designBlueprintMapper.update(designBlueprint);

        return bluePrint;
    }

    public Long insert(DesignBlueprint designBlueprint) {
        return designBlueprintMapper.insert(designBlueprint);
    }

    public DesignBlueprint getById(Long id) {
        return designBlueprintMapper.getById(id);
    }

    public void delete(Long id) {
        List<ProductBlueprintLink> links = productBlueprintLinkMapper.getByBlueprintId(id);
        if (links != null)
            throw new BaseException(MessageConstant.CANNOT_DELETE);
        DesignBlueprint b = designBlueprintMapper.getById(id);
        List<DesignBlueprint.BluePrint> list = b.getBluePrint();
        list.forEach(item -> deleteFile(item.getId()));
        designBlueprintMapper.delete(id);
    }

    public void deleteFile(Long id) {
        RDMParamVO<PersistObjectIdsModifierDTO> paramVO = new RDMParamVO<>();
        PersistObjectIdsModifierDTO ids = new PersistObjectIdsModifierDTO();
        List<Long> list = new ArrayList<>();
        list.add(id);
        ids.setIds(list);
        paramVO.setParams(ids);
        paramVO.setApplicationId(appId);
        fileDelegatorService.batchDelete(paramVO);
    }

    public PageResult page(SearchQueryDTO query) {
        List<DesignBlueprint> record = designBlueprintMapper.pageDesignBlueprints(query);
        Long total = designBlueprintMapper.count(query);

        return PageResult.builder()
                .total(total)
                .records(record)
                .build();
    }

    public List<DesignBlueprint> getByProductId(Long productId) {
        List<Long> ids = productBlueprintLinkMapper.getByProductId(productId).stream().map(link -> link.getBlueprintId()).toList();
        return designBlueprintMapper.getByIds(ids);
    }

    public void update(DesignBlueprint designBlueprint) {
        designBlueprintMapper.update(designBlueprint);
    }


}
