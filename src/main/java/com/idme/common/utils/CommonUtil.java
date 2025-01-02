package com.idme.common.utils;

import com.huawei.innovation.rdm.coresdk.basic.dto.ObjectReferenceParamDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.idme.common.constant.ColumnConstant;
import com.idme.pojo.dto.SearchQueryDTO;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CommonUtil {
    public static QueryRequestVo queryConvert(SearchQueryDTO query) {
        QueryRequestVo q = QueryRequestVo.build();
        if (query.getId() != null)
            q.addCondition("id", ConditionType.EQUAL, query.getId());
        else if (query.getName() != null)
            q.addCondition("name", ConditionType.LIKE, query.getName());
        return q;
    }

    public static RDMPageVO pageConvert(SearchQueryDTO query) {
        RDMPageVO p = new RDMPageVO();
        p.setCurPage(query.getPage());
        p.setPageSize(query.getPageSize());
        return p;
    }

    public static PersistObjectIdDecryptDTO fetchIdConvert(Long id) {
        PersistObjectIdDecryptDTO p = new PersistObjectIdDecryptDTO();
        p.setId(id);
        return p;
    }

    public static PersistObjectIdModifierDTO deleteIdConvert(Long id) {
        PersistObjectIdModifierDTO p = new PersistObjectIdModifierDTO();
        p.setId(id);
        return p;
    }

    public static ObjectReferenceParamDTO linkIdConvert(Long id) {
        ObjectReferenceParamDTO o = new ObjectReferenceParamDTO();
        o.setId(id);
        return o;
    }

    public static QueryRequestVo linkQueryConvert(Long sourceId, Long targetId){
        QueryRequestVo q = QueryRequestVo.build();

        if(sourceId != null)
            q.addCondition(ColumnConstant.SOURCE_ID, ConditionType.EQUAL, sourceId);
        q.and();
        if(targetId != null)
            q.addCondition(ColumnConstant.TARGET_ID, ConditionType.EQUAL, targetId);
        return q;
    }

    public static <S, T> T resConvert(S source, Class<T> targetClazz) {
        if (source == null)
            return null;
        try {
            T target = targetClazz.newInstance();
            BeanUtils.copyProperties(target, source);
            return target;
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <S, T> List<T> ListResConvert(List<S> source, Class<T> clazz) {
        if (source == null || source.isEmpty())
            return null;
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
