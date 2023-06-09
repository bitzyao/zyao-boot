package com.zyao.controller;

import com.zyao.common.api.sys.Email;
import com.zyao.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/send-email")
    public void sendEmail() {
        Email email = new Email();
        email.setEmail("1215668319@qq.com");
        email.setSubject("测试");
        email.setContent("测试");
        email.setIsHtml(true);
        emailService.sendEmail(email);
    }
}