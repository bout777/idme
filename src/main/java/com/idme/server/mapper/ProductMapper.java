package com.idme.server.mapper;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
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
        PersistObjectIdDecryptDTO Id = new PersistObjectIdDecryptDTO();
        Id.setId(id);
        ProductViewDTO productView = productDelegator.get(Id);

        return CommonUtil.resConvert(productView, Product.class);
    }

    public List<Product> pageProduct(SearchQueryDTO query) {
        QueryRequestVo q= CommonUtil.queryConvert(query);
        RDMPageVO p = CommonUtil.pageConvert(query);
        List<ProductViewDTO> productViews = productDelegator.find(q,p);
        return CommonUtil.ListResConvert(productViews, Product.class);
    }

    public Long count(SearchQueryDTO query){
        return productDelegator.count(CommonUtil.queryConvert(query));
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
