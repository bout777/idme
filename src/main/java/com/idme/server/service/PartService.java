package com.idme.server.service;

import com.idme.common.constant.MessageConstant;
import com.idme.common.exception.BaseException;
import com.idme.common.result.PageResult;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Part;
import com.idme.pojo.entity.PartClsDefinition;
import com.idme.pojo.relation.ProductPartLink;
import com.idme.server.mapper.PartClsMapper;
import com.idme.server.mapper.PartMapper;
import com.idme.server.mapper.ProductBlueprintLinkMapper;
import com.idme.server.mapper.ProductPartLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {
    @Autowired
    private PartMapper partMapper;
    @Autowired
    private PartClsMapper partClsMapper;
    @Autowired
    private ProductPartLinkMapper partLinkMapper;
    @Autowired
    private ProductBlueprintLinkMapper blueprintLinkMapper;

    public void insert(Part part) {
        if (part.getDescription().indexOf('$') >= 0)
            throw new BaseException(MessageConstant.ILLEGAL_INPUT);
        partMapper.insert(part);
    }

    public void update(Part part) {
        if (part.getDescription().indexOf('$') >= 0)
            throw new BaseException(MessageConstant.ILLEGAL_INPUT);
        partMapper.update(part);
    }

    public PageResult page(SearchQueryDTO query) {
        List<Part> list = partMapper.pagePart(query);
        Long total = partMapper.count(query);
        return PageResult.builder()
                .total(total)
                .records(list)
                .build();
    }

    public void delete(Long id) {
        List<ProductPartLink> links = partLinkMapper.get(null, id);
        // 判断该零件是否被产品关联
        if (links != null && !links.isEmpty())
            throw new BaseException(MessageConstant.CANNOT_DELETE);
        partMapper.delete(id);
    }

    public List<PartClsDefinition> listDefs() {
        return partClsMapper.getAllClsDefs();
    }

    public List<Part> getByProductId(Long id) {
        List<ProductPartLink> links = partLinkMapper.get(id, null);
        List<Long> ids = links.stream().map(ProductPartLink::getPartId).toList();
        return partMapper.getByIds(ids);
    }


}
