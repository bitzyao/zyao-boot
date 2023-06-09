package com.zyao.config.quartz;

import com.zyao.service.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/9 18:13
 * @Description
 */
@Component
public class InitQuartz {
    @Autowired
    private QuartzJobService quartzJobService;
    //     @PostConstruct是Java自带的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
    //    从Java EE5规范开始，Servlet中增加了两个影响Servlet生命周期的注解，@PostConstruct和@PreDestroy，这两个注解被用来修饰一个非静态的void（）方法。
    @PostConstruct
    public void init() {
        quartzJobService.initSchedule();
    }
}
