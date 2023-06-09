package com.zyao.common.api.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/9 18:08
 * @Description
 */
@Data
@TableName("sys_quartz_job")
public class QuartzJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String Id;
    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 任务类名
     */
    private String jobClassName;


    /**
     * 任务状态
     */
    private String jobStatus;

    /**
     * 任务表达式
     */
    private String cronExpression;

}
