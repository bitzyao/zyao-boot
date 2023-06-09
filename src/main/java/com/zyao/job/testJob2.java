package com.zyao.job;

import com.zyao.common.api.sys.Email;
import com.zyao.service.EmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/9 18:22
 * @Description
 */
public class testJob2 implements Job {
    @Autowired
    private EmailService emailService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("————————————————定时任务Test2执行开始——————————————————————");
//        Email email = new Email();
//        email.setEmail("1215668319@qq.com");
//        email.setSubject("定时任务Test2");
//        email.setContent("定时任务Test2");
//        email.setIsHtml(true);
//        emailService.sendEmail(email);
        System.out.println("————————————————定时任务Test2执行结束——————————————————————");
    }
}
