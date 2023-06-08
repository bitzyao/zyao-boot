package com.zyao.common.api.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    private String id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 发送邮箱
     */
    private String email;

    /**
     * 抄送邮箱
     */
    private String copyEmail;

    /**
     * 状态
     */
    private String status;

    /**
     * isHtml
     */
    private Boolean isHtml;
}
