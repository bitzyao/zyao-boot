package com.zyao.common.aspect;

import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.zyao.common.aspect.annotation.AutoLog;
import com.zyao.common.aspect.annotation.MyCacheable;
import com.zyao.common.constant.CommonConstant;
import com.zyao.common.constant.enums.ModuleType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/7 15:03
 * @Description 系统日志切面处理类
 */
@Aspect
@Component
public class AutoLogAspect {


//    @Resource
//    private BaseCommonService baseCommonService;

    @Pointcut("@annotation(com.zyao.common.aspect.annotation.AutoLog)")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void beforeAdvice() {
        System.out.println("日志切面beforeAdvice...");
    }

    @After("logPointCut()")
    public void afterAdvice() {
        System.out.println("日志切面afterAdvice...");
    }


    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AutoLog syslog = method.getAnnotation(AutoLog.class);


//        long beginTime = System.currentTimeMillis();
        //执行方法
        System.out.println("日志切面1");
        Object result = joinPoint.proceed();
        System.out.println("日志切面2");
        //执行时长(毫秒)
//        long time = System.currentTimeMillis() - beginTime;

        //保存日志
//        saveSysLog(joinPoint, time, result);

        return result;
    }

    public void saveSysLog(ProceedingJoinPoint joinPoint,long time,Object result){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        AutoLog syslog = method.getAnnotation(AutoLog.class);

    }
}
