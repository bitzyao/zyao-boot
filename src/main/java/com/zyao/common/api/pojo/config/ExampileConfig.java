package com.zyao.common.api.pojo.config;

import com.zyao.common.api.pojo.Example;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/8 10:20
 * @Description 初始化Example配置类
 */
@EnableConfigurationProperties(Example.class)
public class ExampileConfig {
}
