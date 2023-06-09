package com.zyao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyao.common.api.sys.QuartzJob;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/9 18:10
 * @Description
 */
public interface QuartzJobService extends IService<QuartzJob> {

    /**
     * 初始化定时任务
     */
    void initSchedule();

    /**
     * 添加定时任务
     *
     * @param job 任务实体类
     */
    void addJob(QuartzJob job);

    /**
     * 修改定时任务
     *
     * @param job 任务实体类
     */
    void modifyJob(QuartzJob job);

    /**
     * 删除定时任务
     *
     * @param job 任务实体类
     */
    void deleteJob(QuartzJob job);

    /**
     * 暂停定时任务
     *
     * @param job 任务实体类
     */
    void pauseJob(QuartzJob job);

    /**
     * 恢复定时任务
     *
     * @param job 任务实体类
     */
    void resumeJob(QuartzJob job);

    /**
     * 立即执行定时任务
     *
     * @param job 任务实体类
     */
    void runJob(QuartzJob job);

}
