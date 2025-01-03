package com.idme.server.mapper;

import com.huawei.innovation.rdm.bean.entity.XDMFileModel;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.dto.entity.XDMFileModelViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.DesignBlueprintDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintViewDTO;
import com.idme.common.constant.ColumnConstant;
import com.idme.common.utils.CommonUtil;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.DesignBlueprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DesignBlueprintMapper {
    @Autowired
    private DesignBlueprintDelegator designBlueprintDelegator;

    public Long insert(DesignBlueprint designBlueprint) {
        DesignBlueprintCreateDTO dto = new DesignBlueprintCreateDTO();

        dto.setBuleprintDescription(designBlueprint.getBlueprintDescription());
        List<XDMFileModel> list = CommonUtil.ListResConvert(designBlueprint.getBluePrint(), XDMFileModel.class);
        dto.setBluePrint(list);
        DesignBlueprintViewDTO viewDTO = designBlueprintDelegator.create(dto);
        return viewDTO.getId();
    }

    public DesignBlueprint getById(Long id) {

        DesignBlueprintViewDTO viewDTO = designBlueprintDelegator.get(CommonUtil.fetchIdConvert(id));
        List<DesignBlueprint.BluePrint> reslist = CommonUtil.ListResConvert(viewDTO.getBluePrint(), DesignBlueprint.BluePrint.class);

        DesignBlueprint res = DesignBlueprint.builder()
                .id(viewDTO.getId())
                .blueprintDescription(viewDTO.getBuleprintDescription())
                .bluePrint(reslist)
                .build();

        return res;
    }

    public void delete(Long id) {
        designBlueprintDelegator.delete(CommonUtil.deleteIdConvert(id));
    }

    public void update(DesignBlueprint designBlueprint) {
        //后期尝试用Convert工具封装
        DesignBlueprintUpdateDTO dto = new DesignBlueprintUpdateDTO();
        dto.setId(designBlueprint.getId());
        dto.setBuleprintDescription(designBlueprint.getBlueprintDescription());

        List<XDMFileModel> mdlist = CommonUtil.ListResConvert(designBlueprint.getBluePrint(), XDMFileModel.class);
        dto.setBluePrint(mdlist);

        designBlueprintDelegator.update(dto);
    }

    public List<DesignBlueprint> pageDesignBlueprints(SearchQueryDTO query) {
        QueryRequestVo q = CommonUtil.queryConvert(query);
        RDMPageVO p = CommonUtil.pageConvert(query);
        List<DesignBlueprintViewDTO> viewList = designBlueprintDelegator.find(q, p);
        List<DesignBlueprint> resList = viewList.stream().map(this::convert).toList();
        return resList;
    }

    public List<DesignBlueprint> getByIds(List<Long> ids) {
        QueryRequestVo q = new QueryRequestVo();
        q.addCondition(ColumnConstant.ID, ConditionType.IN, ids);
        RDMPageVO p = new RDMPageVO();
        List<DesignBlueprintViewDTO> viewList = designBlueprintDelegator.find(q, p);
        List<DesignBlueprint> resList = viewList.stream().map(this::convert).toList();
        return resList;
    }

    public Long count(SearchQueryDTO query) {
        QueryRequestVo q = CommonUtil.queryConvert(query);
        return designBlueprintDelegator.count(q);
    }

    private DesignBlueprint convert(DesignBlueprintViewDTO source) {
        return DesignBlueprint.builder()
                .id(source.getId())
                .blueprintDescription(source.getBuleprintDescription())
                .bluePrint(CommonUtil.ListResConvert(source.getBluePrint(), DesignBlueprint.BluePrint.class))
                .build();
    }

    private XDMFileModel convert(DesignBlueprint.BluePrint source) {
        XDMFileModel xdmFileModel = new XDMFileModel();
        xdmFileModel.setName(source.getName());
        xdmFileModel.setId(source.getId());
        return xdmFileModel;
    }

    private DesignBlueprint.BluePrint convert(XDMFileModelViewDTO source) {
        DesignBlueprint.BluePrint bluePrint = DesignBlueprint.BluePrint.builder()
                .name(source.getName())
                .id(source.getId())
                .build();
        return bluePrint;
    }

}
