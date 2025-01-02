package com.idme.server.mapper;

import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.xdm.delegator.ClassificationNodeDelegator;
import com.huawei.innovation.rdm.xdm.delegator.EXADefinitionDelegator;
import com.huawei.innovation.rdm.xdm.delegator.EXADefinitionLinkDelegator;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeViewDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionViewDTO;
import com.huawei.innovation.rdm.xdm.dto.relation.EXADefinitionLinkListViewDTO;
import com.huawei.innovation.rdm.xdm.dto.relation.EXADefinitionLinkViewDTO;
import com.idme.pojo.entity.PartAttrDefinition;
import com.idme.pojo.entity.PartClsDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class PartClsMapper {
    @Autowired
    private ClassificationNodeDelegator classificationNodeDelegator;
    @Autowired
    private EXADefinitionDelegator exaDefinitionDelegator;
    @Autowired
    private EXADefinitionLinkDelegator exaDefinitionLinkDelegator;

    public Long getTotalCount() {
        return classificationNodeDelegator.count(new QueryRequestVo());
    }

    public List<PartClsDefinition> getAllClsDefs() {
        //用于传入函数的空查询请求
        QueryRequestVo q = QueryRequestVo.build();
        RDMPageVO p = new RDMPageVO();

        //获取所有分类
        List<ClassificationNodeViewDTO> cls = classificationNodeDelegator.find(q, p);
        //获取分类和属性的关系表
        List<EXADefinitionLinkViewDTO> linkList = exaDefinitionLinkDelegator.find(q, p);
        //获取所有属性
        List<EXADefinitionViewDTO> list = exaDefinitionDelegator.find(q, p);



        //建立属性id->属性的映射
        HashMap<Long, PartAttrDefinition> attrMap = new HashMap<>();
        for (EXADefinitionViewDTO e : list) {
            attrMap.put(e.getId(), PartAttrDefinition.builder().name(e.getName()).enName(e.getNameEn()).build());
        }

        //建立分类id->属性列表的映射
        HashMap<Long, List<PartAttrDefinition>> attrLinkMap = new HashMap<>();
        for (EXADefinitionLinkViewDTO l : linkList) {
            Long attrId = l.getSource().getId();
            Long clsId = l.getTarget().getId();
            if (attrLinkMap.containsKey(clsId)) {
                attrLinkMap.get(clsId).add(attrMap.get(attrId));
            } else {
                List<PartAttrDefinition> ls = new ArrayList<>();
                ls.add(attrMap.get(attrId));
                attrLinkMap.put(clsId, ls);
            }
        }

        //转义结果
        List<PartClsDefinition> res = cls.stream().map(c -> {
            return PartClsDefinition.
                    builder().id(c.getId()).description(c.getDescription()).name(c.getName()).build();
        }).toList();

        //填入属性列表
        for (PartClsDefinition c : res) {
            if (attrLinkMap.containsKey(c.getId())) {
                c.setAttrDefs(attrLinkMap.get(c.getId()));
            }
        }

        return res;
    }

}
