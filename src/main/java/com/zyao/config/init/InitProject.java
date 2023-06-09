package com.zyao.config.init;

import com.zyao.service.QuartzJobService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/9 13:48
 * @Description
 */
@Configuration
public class InitProject implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        // 可以在这里初始化一些信息，例如定时任务的初始化
        System.out.println("项目启动前数据初始化");
    }
}
