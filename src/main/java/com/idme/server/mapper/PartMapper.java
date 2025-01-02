package com.idme.server.mapper;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.innovation.rdm.coresdk.basic.dto.MasterIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.PartDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.*;
import com.idme.common.constant.ColumnConstant;
import com.idme.common.json.JacksonObjectMapper;
import com.idme.common.properties.JwtProperties;
import com.idme.common.utils.CommonUtil;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Part;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Component
public class PartMapper {
    @Autowired
    private PartDelegator partDelegator;

    public void insert(Part part) {
        PartCreateDTO partCreateDTO = CommonUtil.resConvert(part, PartCreateDTO.class);

        partCreateDTO.setMaster(new PartMasterCreateDTO());
        partCreateDTO.setBranch(new PartBranchCreateDTO());

        String attrs = attrsConvert(part.getAttrs());
        partCreateDTO.setDescription(part.getDescription()+'$'+part.getCls()+'$'+attrs);

        PartViewDTO partViewDTO = partDelegator.create(partCreateDTO);
    }

    public void update(Part part) {
        PartUpdateByAdminDTO partUpdateDTO = CommonUtil.resConvert(part, PartUpdateByAdminDTO.class);
        String attrs = attrsConvert(part.getAttrs());
        partUpdateDTO.setDescription(part.getDescription()+'$'+part.getCls()+'$'+attrs);
        partDelegator.updateByAdmin(partUpdateDTO);
    }

    public void delete(Long id) {
        MasterIdModifierDTO obj = new MasterIdModifierDTO();
        obj.setMasterId(id);
        partDelegator.delete(obj);
    }

    public List<Part> getByIds(List<Long> ids) {
        QueryRequestVo q = new QueryRequestVo();
        q.addCondition(ColumnConstant.ID, ConditionType.IN, ids);
        RDMPageVO p = new RDMPageVO();
        List<PartViewDTO> list = partDelegator.find(q, p);
        List<Part> res = list.stream().map(this::convert).toList();
        return res;
    }

    public List<Part> pagePart(SearchQueryDTO query) {
        QueryRequestVo q = CommonUtil.queryConvert(query);
        RDMPageVO p = CommonUtil.pageConvert(query);

        List<PartViewDTO> list = partDelegator.find(q, p);

        List<Part> res = list.stream().map(this::convert).toList();
        return res;
    }

    public Long count(SearchQueryDTO query) {
        return partDelegator.count(CommonUtil.queryConvert(query));
    }

    private Part convert(PartViewDTO source) {
        Part part = CommonUtil.resConvert(source, Part.class);
        if(source.getDescription()==null)
            return part;
        String desc = source.getDescription();
        String[] strs = desc.split("\\$");
        if (strs.length<2)
            return part;
        String cls = strs[1];
        part.setCls(cls);
        if(strs.length<3)
            return part;
        String attrs = strs[2];
        part.setAttrs(attrsConvert(attrs));
        part.setDescription(part.getDescription().substring(0, part.getDescription().indexOf('$')));
        return part;
    }

    private List<Part.PartAttr> attrsConvert(String attrs) {
        return JSONObject.parseArray(attrs).toJavaList(Part.PartAttr.class);
    }

    private String attrsConvert(List<Part.PartAttr> attrs) {
        return JSONObject.toJSONString(attrs);
    }

}
