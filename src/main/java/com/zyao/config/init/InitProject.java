package com.zyao.config.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/9 13:48
 * @Description
 */
@Component
public class InitProject implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("-----项目初始化定时任务开始------");
    }
}
