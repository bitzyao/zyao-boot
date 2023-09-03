package com.zyao.common.aspect;

import com.zyao.common.aspect.annotation.MyCacheable;
import com.zyao.job.utils.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/7 13:36
 * @Description 缓存切面
 */
@Aspect
@Component
public class CacheAspect {

    @Autowired
    private RedisUtil redisUtil;

    @Around("@annotation(myCacheable)")
    public Object cache(ProceedingJoinPoint joinPoint, MyCacheable myCacheable) throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        String key = myCacheable.key().isEmpty() ? methodName : myCacheable.key();

        int expireSeconds = myCacheable.expireSeconds();

        String folder = myCacheable.folder();
        if (!StringUtils.isEmpty(folder)) {
            key = folder + ":" + key;
        }
        boolean isHas= redisUtil.hasKey(key);
        Object result;
        System.out.println("缓存切面1");
        if (isHas) {
            result = redisUtil.get(key);
        }else{
            result = joinPoint.proceed();

            redisUtil.set(key, result.toString(), expireSeconds);
        }
        System.out.println("缓存切面2");
        return result;
    }


    @Before("@annotation(myCacheable)")
    public void beforeAdvice(MyCacheable myCacheable) {
        System.out.println("缓存切面beforeAdvice...");
    }

    @After("@annotation(myCacheable)")
    public void afterAdvice(MyCacheable myCacheable) {
        System.out.println("缓存切面afterAdvice...");
    }
}
