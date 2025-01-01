package com.idme.server.mapper;

import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductPartLinkDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductBlueprintLinkViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductPartLinkCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductPartLinkViewDTO;
import com.idme.common.constant.ColumnConstant;
import com.idme.common.utils.CommonUtil;
import com.idme.pojo.relation.ProductBlueprintLink;
import com.idme.pojo.relation.ProductPartLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductPartLinkMapper {
    @Autowired
    private ProductPartLinkDelegator linkDelegator;

    public void insert(Long productId, Long partId) {
        ProductPartLinkCreateDTO dto = convert(new ProductPartLink(productId, partId));
        linkDelegator.create(dto);
    }

    public void deleteByProductId(Long productId) {
        DeleteByConditionVo vo = new DeleteByConditionVo();
        QueryRequestVo q = QueryRequestVo.build();
        q.addCondition(ColumnConstant.SOURCE_ID, ConditionType.EQUAL, productId);
        vo.setCondition(q);
        linkDelegator.deleteByCondition(vo);
    }

    public void deleteByPartId(Long partId) {
        DeleteByConditionVo vo = new DeleteByConditionVo();
        QueryRequestVo q = QueryRequestVo.build();
        q.addCondition(ColumnConstant.TARGET_ID, ConditionType.EQUAL, partId);
        linkDelegator.deleteByCondition(vo);
    }

    public List<ProductPartLink> getByProductId(Long productId) {
        QueryRequestVo q = QueryRequestVo.build();
        q.addCondition(ColumnConstant.SOURCE_ID, ConditionType.EQUAL, productId);
        RDMPageVO p = new RDMPageVO();
        List<ProductPartLinkViewDTO> views = linkDelegator.find(q, p);
        if (views == null)
            return null;
        List<ProductPartLink> res = views.stream().map(this::convert).toList();
        return res;
    }

    private ProductPartLinkCreateDTO convert(ProductPartLink source) {
        ProductPartLinkCreateDTO target = new ProductPartLinkCreateDTO();
        target.setSource(CommonUtil.linkIdConvert(source.getProductId()));
        target.setTarget(CommonUtil.linkIdConvert(source.getPartId()));
        return target;
    }

    private ProductPartLink convert(ProductPartLinkViewDTO source) {
        ProductPartLink target = ProductPartLink.builder()
                .productId(source.getSource().getId())
                .partId(source.getTarget().getId())
                .build();
        return target;
    }
}
