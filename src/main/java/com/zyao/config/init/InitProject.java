package com.zyao.config.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/9 13:48
 * @Description
 */
@Configuration
@Slf4j
public class InitProject implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        // 可以在这里初始化一些信息，例如定时任务的初始化
        log.info("项目启动前数据初始化");
    }
}
