package com.zyao.service.ipml;

import cn.hutool.Hutool;
import cn.hutool.core.util.IdUtil;
import com.zyao.common.api.sys.Email;
import com.zyao.common.utils.EmailUtil;
import com.zyao.mapper.sys.EmailMapper;
import com.zyao.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/8 14:49
 * @Description Email业务实现类
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public void sendEmail(Email email)  {

        Boolean result = emailUtil.sendEmail(email);
        if(result){
            email.setStatus("发送成功");
        }else{
            email.setStatus("发送失败");
        }
        email.setId(IdUtil.simpleUUID());
        emailMapper.insert(email);
    }
}
