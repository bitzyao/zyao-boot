package com.zyao.job.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/8 11:44
 * @Description 整个Hutool发送邮件工具
 */
@Component
public class EmailUtil {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private Integer port;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.sslEnable}")
    private Boolean sslEnable;

}
