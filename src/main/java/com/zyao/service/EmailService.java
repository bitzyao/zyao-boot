package com.zyao.service;

import com.zyao.common.api.sys.Email;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/8 14:46
 * @Description Emial业务接口
 */
public interface EmailService {
    void sendEmail(Email email);
}
