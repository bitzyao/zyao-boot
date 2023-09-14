package com.zyao.common.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/7 13:17
 * @Description 缓存注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCacheable {
    String key() default ""; // key值
    String folder() default ""; // Redis文件夹
    int expireSeconds() default 60; // 过期时间
    boolean isArgAsKey() default false;
}
