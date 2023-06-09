package com.zyao.common.api.sys;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/9 16:49
 * @Description
 */
@Data
@TableName("sys_job")
public class JobEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

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
    private String jobDescription;

    /**
     * 执行类
     */
    private String jobClass;

    /**
     * 执行时间
     */
    private String cronExpression;

    /**
     * 任务状态（0：禁用；1：启用）
     */
    private Integer jobStatus;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
