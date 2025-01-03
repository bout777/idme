package com.idme.server.mapper;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductViewDTO;
import com.idme.common.utils.CommonUtil;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    @Autowired
    private ProductDelegator productDelegator;

    public Product getById(Long id) {
        ProductViewDTO productView = productDelegator.get(CommonUtil.fetchIdConvert(id));
        return CommonUtil.resConvert(productView, Product.class);
    }

    public Product getByName(String name) {
        QueryRequestVo q = QueryRequestVo.build();
        q.addCondition("productName", ConditionType.EQUAL, name);

        RDMPageVO p = new RDMPageVO();
        List<ProductViewDTO> views = productDelegator.find(q, p);
        if (views == null || views.isEmpty())
            return null;
        return CommonUtil.resConvert(views.get(0), Product.class);
    }

    public List<Product> pageProduct(SearchQueryDTO query) {
        QueryRequestVo q = QueryRequestVo.build();
        if (query.getName() != null)
            q.addCondition("productName", ConditionType.LIKE, query.getName());

        RDMPageVO p = CommonUtil.pageConvert(query);
        List<ProductViewDTO> productViews = productDelegator.find(q, p);
        return CommonUtil.ListResConvert(productViews, Product.class);
    }

    public Long count(SearchQueryDTO query) {
        QueryRequestVo q = QueryRequestVo.build();
        if (query.getName() != null)
            q.addCondition("productName", ConditionType.LIKE, query.getName());
        return productDelegator.count(q);
    }

    public void insert(Product product) {
        ProductCreateDTO dto = CommonUtil.resConvert(product, ProductCreateDTO.class);

        productDelegator.create(dto);
    }

    public void update(Product product) {
        ProductUpdateDTO dto = CommonUtil.resConvert(product, ProductUpdateDTO.class);
        productDelegator.update(dto);
    }

    public void deleteById(Long id) {
        PersistObjectIdModifierDTO dto = new PersistObjectIdModifierDTO();
        dto.setId(id);
        productDelegator.delete(dto);
    }
}
