package com.zyao.service.sys;

import com.zyao.modal.sys.SysQuartzJob;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ayao
* @description 针对表【sys_quartz_job】的数据库操作Service
* @createDate 2023-09-03 15:23:48
*/
public interface SysQuartzJobService extends IService<SysQuartzJob> {

    void initSchedule();

    /**
     * 添加定时任务
     *
     * @param job 任务实体类
     */
    void addJob(SysQuartzJob job);

    /**
     * 修改定时任务
     *
     * @param job 任务实体类
     */
    void modifyJob(SysQuartzJob job);

    /**
     * 删除定时任务
     *
     * @param job 任务实体类
     */
    void deleteJob(SysQuartzJob job);

    /**
     * 暂停定时任务
     *
     * @param job 任务实体类
     */
    void pauseJob(SysQuartzJob job);

    /**
     * 恢复定时任务
     *
     * @param job 任务实体类
     */
    void resumeJob(SysQuartzJob job);

    /**
     * 立即执行定时任务
     *
     * @param job 任务实体类
     */
    void runJob(SysQuartzJob job);
}
