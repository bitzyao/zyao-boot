package com.zyao.controller.sys;

import com.zyao.controller.BaseController;
import com.zyao.modal.sys.SysEmail;
import com.zyao.service.sys.SysEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/9/15 17:15
 * @Description
 */
@RestController
@RequestMapping("/Email")
public class SysEmailController extends BaseController {

    @Autowired
    private SysEmailService sysEmailService;

    @RequestMapping("/send-email")
    public void sendEmail() {
        SysEmail email = new SysEmail();
        email.setEmail("1215668319@qq.com");
        email.setSubject("测试");
        email.setContent("测试");
        email.setIsHtml(true);
        sysEmailService.sendEmail(email);
    }
}
