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

    @PostConstruct
    public void init() {
        quartzJobService.initSchedule();
    }
}
