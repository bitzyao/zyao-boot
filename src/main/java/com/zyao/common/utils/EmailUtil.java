package com.zyao.common.utils;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.zyao.common.api.sys.Email;
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

    public Boolean sendEmail(Email email, File... files) {
        MailAccount account = new MailAccount();
        account.setHost(host);
        account.setPort(port);
        account.setAuth(true);
        account.setFrom(from);
        account.setUser(from);
        account.setPass(password);
        account.setSslEnable(sslEnable); // QQ邮箱必须开启
        try {
            MailUtil.send(account, email.getEmail(), email.getSubject(), email.getContent(), email.getIsHtml(),files);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }


}
