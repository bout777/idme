package com.idme.server.mapper;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.PartDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.*;
import com.idme.common.json.JacksonObjectMapper;
import com.idme.common.properties.JwtProperties;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Part;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Component
public class PartMapper {
    @Autowired
    private PartDelegator partDelegator;

    public void insert(Part part){
        PartCreateDTO partCreateDTO = new PartCreateDTO();

        partCreateDTO.setName(part.getName());
        partCreateDTO.setPartName(part.getPartName());
        partCreateDTO.setDescription(part.getDescription());
        partCreateDTO.setMaster(new PartMasterCreateDTO());
        partCreateDTO.setBranch(new PartBranchCreateDTO());

        PartViewDTO partViewDTO = partDelegator.create(partCreateDTO);

        JSONArray Clsattrs= partViewDTO.getClsAttrs();
        System.out.println(Clsattrs);
    }

    public void update(Part part) throws JsonProcessingException {
        PartUpdateByAdminDTO partUpdateDTO = new PartUpdateByAdminDTO();

        partUpdateDTO.setName(part.getName());
        partUpdateDTO.setPartName(part.getPartName());
        partUpdateDTO.setDescription(part.getDescription());
        partUpdateDTO.setId(part.getId());

        partDelegator.updateByAdmin(partUpdateDTO);
    }

    public List<Part> pagePart(SearchQueryDTO query){
        QueryRequestVo q = QueryRequestVo.build();
        if(query.getId()!=null)
            q.addCondition("id", ConditionType.EQUAL, query.getId());
        if(query.getName()!=null)
            q.addCondition("name", ConditionType.LIKE,'%'+query.getName()+'%');

        List<PartViewDTO> list = partDelegator.find(q, new RDMPageVO(query.getPage(), query.getPageSize()));

        List<Part> res = list.stream().map(item -> {
            Part part = new Part();
            try {
                BeanUtils.copyProperties(part, item);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            return part;
        }).toList();

        return res;
    }

}
