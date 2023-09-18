package com.zyao.service.sys;

import com.zyao.modal.sys.SysEmail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ayao
* @description 针对表【sys_email】的数据库操作Service
* @createDate 2023-09-03 15:23:45
*/
public interface SysEmailService extends IService<SysEmail> {
    void sendEmail(SysEmail email);
}
