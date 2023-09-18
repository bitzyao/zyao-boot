package com.zyao.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyao.mapper.sys.SysQuartzJobMapper;
import com.zyao.modal.sys.SysQuartzJob;
import com.zyao.service.sys.SysQuartzJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/9 18:10
 * @Description
 */
@Service
public class SysQuartzJobServiceImpl extends ServiceImpl<SysQuartzJobMapper, SysQuartzJob> implements SysQuartzJobService {

    @Autowired
    private Scheduler scheduler;

    /**
     * 初始化定时任务
     */
    @Override
    public void initSchedule() {
        List<SysQuartzJob> jobList = this.list();
        for (SysQuartzJob job : jobList) {
            if (job.getIsStart()) {
                addJob(job);
            }
        }
    }

    /**
     * 添加定时任务
     *
     * @param job 任务实体类
     */
    @Override
    public void addJob(SysQuartzJob job) {
        try {
            // 构建JobDetail
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(job.getJobClassName()))
                    .withIdentity(job.getJobName(), job.getJobGroup())
                    .withDescription(job.getDescription())
                    .build();

            // 构建CronTrigger
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(job.getJobName(), job.getJobGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                    .build();

            // 注册JobDetail和CronTrigger到Scheduler
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改定时任务
     *
     * @param job 任务实体类
     */
    @Override
    public void modifyJob(SysQuartzJob job) {
        try {
            // 获取旧的CronTrigger
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            CronTrigger oldCronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 构建新的CronTrigger
            CronTrigger newCronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(job.getJobName(), job.getJobGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                    .build();

            // 获取旧的JobDetail
            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
            JobDetail oldJobDetail = scheduler.getJobDetail(jobKey);

            // 构建新的JobDetail
            JobDetail newJobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(job.getJobClassName()))
                    .withIdentity(job.getJobName(), job.getJobGroup())
                    .withDescription(job.getDescription())
                    .build();

            // 停止旧的定时任务
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);

            // 注册新的JobDetail和CronTrigger到Scheduler
            scheduler.scheduleJob(newJobDetail, newCronTrigger);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除定时任务
     *
     * @param job 任务实体类
     */
    @Override
    public void deleteJob(SysQuartzJob job) {
        try {
            // 获取旧的CronTrigger
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

            // 获取旧的JobDetail
            JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());

            // 停止旧的定时任务
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停定时任务
     *
     * @param job 任务实体类
     */
    @Override
    public void pauseJob(SysQuartzJob job) {
        try {
            // 获取旧的CronTrigger
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

            // 暂停定时任务
            scheduler.pauseTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复定时任务
     *
     * @param job 任务实体类
     */
    @Override
    public void resumeJob(SysQuartzJob job) {
        try {
            // 获取旧的CronTrigger
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

            // 恢复定时任务
            scheduler.resumeTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 立即执行定时任务
     *
     * @param job 任务实体类
     */
    @Override
    public void runJob(SysQuartzJob job) {
        try {
            // 获取旧的CronTrigger
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

            // 立即执行定时任务
            scheduler.triggerJob(JobKey.jobKey(job.getJobName(), job.getJobGroup()));
//            scheduler.triggerJob(JobKey.jobKey(job.getJobName(), job.getJobGroup()), new JobDataMap(), newCronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
