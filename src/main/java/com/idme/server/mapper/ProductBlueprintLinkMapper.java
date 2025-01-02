package com.idme.server.mapper;

import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductBlueprintLinkDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductBlueprintLinkCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductBlueprintLinkViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductPartLinkViewDTO;
import com.idme.common.constant.ColumnConstant;
import com.idme.common.utils.CommonUtil;
import com.idme.pojo.relation.ProductBlueprintLink;
import com.idme.pojo.relation.ProductPartLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductBlueprintLinkMapper {
    @Autowired
    private ProductBlueprintLinkDelegator linkDelegator;

    public void insert(Long productId, Long blueprintId) {
        ProductBlueprintLinkCreateDTO dto = convert(new ProductBlueprintLink(productId, blueprintId));
        linkDelegator.create(dto);
    }

    public List<ProductBlueprintLink>get(Long productId, Long blueprintId) {
        QueryRequestVo q = CommonUtil.linkQueryConvert(productId, blueprintId);
        RDMPageVO p = new RDMPageVO();
        List<ProductBlueprintLinkViewDTO> views= linkDelegator.find(q,p);
        if (views == null)
            return null;
        List<ProductBlueprintLink> res = views.stream().map(this::convert).toList();
        return res;
    }

    public void deleteByProductId(Long productId) {
        DeleteByConditionVo vo = new DeleteByConditionVo();
        QueryRequestVo q = QueryRequestVo.build();
        q.addCondition(ColumnConstant.SOURCE_ID, ConditionType.EQUAL, productId);
        vo.setCondition(q);
        linkDelegator.deleteByCondition(vo);
    }

    public void deleteByBlueprintId(Long blueprintId) {
        DeleteByConditionVo vo = new DeleteByConditionVo();
        QueryRequestVo q = QueryRequestVo.build();
        q.addCondition(ColumnConstant.TARGET_ID, ConditionType.EQUAL, CommonUtil.linkIdConvert(blueprintId));
        linkDelegator.deleteByCondition(vo);
    }

    public void deleteByProductIdAndBlueprintId(Long productId, Long blueprintId) {
        DeleteByConditionVo vo = new DeleteByConditionVo();
        QueryRequestVo q = QueryRequestVo.build();
        q.and();
        q.addCondition(ColumnConstant.SOURCE_ID, ConditionType.EQUAL, productId);
        q.addCondition(ColumnConstant.TARGET_ID, ConditionType.EQUAL, blueprintId);

        linkDelegator.deleteByCondition(vo);
    }

    public List<ProductBlueprintLink> getByProductId(Long productId) {
        QueryRequestVo q = QueryRequestVo.build();
        q.addCondition(ColumnConstant.SOURCE_ID, ConditionType.EQUAL, productId);
        RDMPageVO p = new RDMPageVO();
        List<ProductBlueprintLinkViewDTO> views = linkDelegator.find(q, p);
        if (views == null)
            return null;
        List<ProductBlueprintLink> res = views.stream().map(this::convert).toList();
        return res;
    }

    public List<ProductBlueprintLink> getByBlueprintId(Long BlueprintId) {
        QueryRequestVo q = QueryRequestVo.build();
        q.addCondition(ColumnConstant.TARGET_ID, ConditionType.EQUAL, BlueprintId);
        RDMPageVO p = new RDMPageVO();
        List<ProductBlueprintLinkViewDTO> views = linkDelegator.find(q, p);
        if (views == null)
            return null;
        List<ProductBlueprintLink> res = views.stream().map(this::convert).toList();
        return res;
    }

    private ProductBlueprintLink convert(ProductBlueprintLinkViewDTO source) {
        return ProductBlueprintLink.builder()
                .productId(source.getSource().getId())
                .blueprintId(source.getTarget().getId())
                .build();
    }

    private ProductBlueprintLinkCreateDTO convert(ProductBlueprintLink source) {
        ProductBlueprintLinkCreateDTO target = new ProductBlueprintLinkCreateDTO();
        target.setSource(CommonUtil.linkIdConvert(source.getProductId()));
        target.setTarget(CommonUtil.linkIdConvert(source.getBlueprintId()));
        return target;
    }
}
