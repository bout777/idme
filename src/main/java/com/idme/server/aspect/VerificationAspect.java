package com.idme.server.aspect;

import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.Authority;
import com.idme.common.constant.MessageConstant;
import com.idme.common.context.BaseContext;
import com.idme.common.exception.BaseException;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class VerificationAspect {
    @Pointcut("execution(* com.idme.server.service.*.*(..)) && @annotation(com.idme.server.annotation.Admin)")
    public void verifyPointCut() {
    }

    @Before("verifyPointCut()")
    public void verify() {
        if (BaseContext.getCurAuthority() != Authority.Admin || BaseContext.getCurAuthority() == null)
            throw new BaseException(MessageConstant.INSUFFICIENT_PERMISSION);
    }
}
