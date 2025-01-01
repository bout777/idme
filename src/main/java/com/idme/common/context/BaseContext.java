package com.idme.common.context;

import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.Authority;

public class BaseContext {

    public static ThreadLocal<Long> threadLocalId = new ThreadLocal<>();
    public static ThreadLocal<Authority> threadLocalAuthority = new ThreadLocal<>();

    public static void setCurId(Long id) {
        threadLocalId.set(id);
    }

    public static Long getCurId() {
        return threadLocalId.get();
    }

    public static void setCurAuthority(Authority authority) {
        threadLocalAuthority.set(authority);
    }

    public static Authority getCurAuthority() {
        return threadLocalAuthority.get();
    }


}
