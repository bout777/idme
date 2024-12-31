package com.idme.common.utils;

import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Part;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CommonUtil {
    public static QueryRequestVo queryConvert(SearchQueryDTO query){
        QueryRequestVo q = QueryRequestVo.build();
        if(query.getId()!=null)
            q.addCondition("id", ConditionType.EQUAL, query.getId());
        if(query.getName()!=null)
            q.addCondition("name", ConditionType.LIKE,'%'+query.getName()+'%');
        return q;
    }

    public static RDMPageVO pageConvert(SearchQueryDTO query){
        RDMPageVO p = new RDMPageVO();
        p.setCurPage(query.getPage());
        p.setPageSize(query.getPageSize());
        return p;
    }

    public static <S,T> T resConvert(S source, Class<T> targetClazz){
        try {
            T target = targetClazz.newInstance();
            BeanUtils.copyProperties(target, source);
            return target;
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <S,T> List<T> ListResConvert(List<S> source, Class<T> clazz) {
        return source.stream().map(s_item -> {
            T t_item = null;
            try {
                t_item = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            try {
                BeanUtils.copyProperties(t_item, s_item);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            return t_item;
        }).toList();
    }
}
