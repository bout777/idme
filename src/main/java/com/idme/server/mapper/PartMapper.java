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

    public void insert(Part part){
        PartCreateDTO partCreateDTO = CommonUtil.resConvert(part, PartCreateDTO.class);

        partCreateDTO.setMaster(new PartMasterCreateDTO());
        partCreateDTO.setBranch(new PartBranchCreateDTO());

        PartViewDTO partViewDTO = partDelegator.create(partCreateDTO);
    }

    public void update(Part part) throws JsonProcessingException {
        PartUpdateByAdminDTO partUpdateDTO = CommonUtil.resConvert(part, PartUpdateByAdminDTO.class);
        partDelegator.updateByAdmin(partUpdateDTO);
    }

    public List<Part> pagePart(SearchQueryDTO query){
        QueryRequestVo q = CommonUtil.queryConvert(query);
        RDMPageVO p = CommonUtil.pageConvert(query);

        List<PartViewDTO> list = partDelegator.find(q, p);

        List<Part> res = CommonUtil.ListResConvert(list, Part.class);

        return res;
    }

}
