package com.idme.server.mapper;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.OrderDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.OrderViewDTO;
import com.idme.common.utils.CommonUtil;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    @Autowired
    private OrderDelegator orderDelegator;

    public Order getById(Long id) {
        PersistObjectIdDecryptDTO o = new PersistObjectIdDecryptDTO();
        o.setId(id);
        OrderViewDTO orderView = orderDelegator.get(o);

        return CommonUtil.resConvert(orderView, Order.class);
    }

    public void delete(Long id) {
        PersistObjectIdModifierDTO o = new PersistObjectIdModifierDTO();
        o.setId(id);
        orderDelegator.delete(o);
    }

    public void insert(Order order) {
        OrderCreateDTO dto = CommonUtil.resConvert(order, OrderCreateDTO.class);
        orderDelegator.create(dto);
    }

    public void update(Order order) {
        OrderUpdateDTO dto = CommonUtil.resConvert(order, OrderUpdateDTO.class);
        orderDelegator.update(dto);
    }

    public Long count(SearchQueryDTO query) {
        return orderDelegator.count(CommonUtil.queryConvert(query));
    }

    public List<Order> pageOrder(SearchQueryDTO query) {
        QueryRequestVo q = CommonUtil.queryConvert(query);
        RDMPageVO p = CommonUtil.pageConvert(query);
        List<OrderViewDTO> list = orderDelegator.find(q,p);
        return CommonUtil.ListResConvert(list, Order.class);
    }
}
