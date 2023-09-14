package com.zyao.common.aspect;

import com.zyao.common.aspect.annotation.MyCacheable;
import com.zyao.job.utils.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

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
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取key值
        String key = myCacheable.key().isEmpty() ? methodName : myCacheable.key();
        Object[] args = joinPoint.getArgs();
        // 获取方法参数
        if(myCacheable.isArgAsKey()){
            for (Object obj: args) {
                key = key +"_"+ obj.toString();
            }
        }
        // 获取过期时间
        int expireSeconds = myCacheable.expireSeconds();

        // 存储文件
        String folder = myCacheable.folder();
        if (!StringUtils.isEmpty(folder)) {
            key = folder + ":" + key;
        }
        boolean isHas= redisUtil.hasKey(key);
        Object result;
        if (isHas) {
            System.out.println("从Redis返回");
            result = redisUtil.get(key);
        }else{
            System.out.println("从数据库返回");
            result = joinPoint.proceed();
            if(Objects.nonNull(result)){
                redisUtil.set(key, result, expireSeconds);
            }
        }

        return result;
    }


//    @Before("@annotation(myCacheable)")
//    public void beforeAdvice(MyCacheable myCacheable) {
//    }
//
//    @After("@annotation(myCacheable)")
//    public void afterAdvice(MyCacheable myCacheable) {
//    }
}
