package com.zyao.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/9 18:22
 * @Description
 */
public class testJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时任务Test执行");
    }
}
