package com.csy.webmvc.aspect;

import com.csy.webmvc.bean.vo.ErrorResult;
import com.csy.webmvc.constant.Code;
import com.csy.webmvc.exception.BusinessException;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/**
 * 控制层切面,在控制层添加错误处理
 *
 * @author caishiyu
 */
@Aspect
@Component
public class ControllerAspect {
    private static final Logger logger = Logger.getLogger(ControllerAspect.class);

    @Pointcut("execution(* com.csy.webmvc.controller.*.*(..))")
    public void controller() {
    }

    /**
     * 处理整个control层异常
     */
    @Around("controller()")
    public String aroundCtrl(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return (String) joinPoint.proceed();
        } catch (BusinessException e) {
            if (e.getCause() != null) {
                logger.info("业务失败", e);
            }
            return ErrorResult.getResult(e.getErrorCode(), e.getMessage()).toJSONString();
        } catch (DataAccessException e) {
            logger.error("数据库异常", e);
            return ErrorResult.getResult(Code._401, e.getMessage()).toJSONString();
        } catch (Exception e) {
            logger.error("代码出错", e);
            return ErrorResult.getResult(Code._400, e.getMessage()).toJSONString();
        }
    }
}
