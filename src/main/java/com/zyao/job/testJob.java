package com.zyao.job;

import com.zyao.modal.sys.SysEmail;
import com.zyao.service.sys.SysEmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/9 18:22
 * @Description
 */
public class testJob implements Job {

    @Autowired
    private SysEmailService sysEmailService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时任务Test执行");
        SysEmail sysEmail = new SysEmail();
        sysEmail.setIsHtml(true);
        sysEmail.setEmail("1215668319@qq.com");
        sysEmail.setSubject("TestEmail");
        sysEmail.setContent("1215668319@qq.com");
        sysEmailService.sendEmail(sysEmail);
    }
}
