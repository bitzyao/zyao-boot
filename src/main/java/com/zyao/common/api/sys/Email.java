package com.zyao.common.api.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/8 13:33
 * @Description 邮件实体
 */
@Data
@TableName("sys_email")
public class Email {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @CreatedDate
    private Date createTime;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 主题
     */
    @TableField("subject")
    private String subject;

    /**
     * 邮件内容
     */
    @TableField("content")
    private String content;

    /**
     * 发送邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 抄送邮箱
     */
    @TableField("copy_name")
    private String copyEmail;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * isHtml
     */
    @TableField("is_html")
    private Boolean isHtml;

    /**
     * 异常信息
     */
    @TableField("exception_info")
    private String exceptionInfo;
}
