package com.zyao.service.sys.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyao.common.utils.EmailUtil;
import com.zyao.mapper.sys.SysEmailMapper;
import com.zyao.modal.sys.SysEmail;
import com.zyao.service.sys.SysEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

/**
* @author ayao
* @description 针对表【sys_email】的数据库操作Service实现
* @createDate 2023-09-03 15:23:45
*/
@Service
public class SysEmailServiceImpl extends ServiceImpl<SysEmailMapper, SysEmail> implements SysEmailService{

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private SysEmailMapper sysEmailMapper;

    @Override
    public void sendEmail(SysEmail email) {

        Boolean result = emailUtil.sendEmail(email,new File("C:\\Users\\F\\Pictures\\Camera Roll\\50dbe2fec3eca335c35fe5e128609bb93350245.jpg"),new File("C:\\Users\\F\\Pictures\\Camera Roll\\50dbe2fec3eca335c35fe5e128609bb93350245.jpg"),new File("C:\\Users\\F\\Pictures\\Camera Roll\\50dbe2fec3eca335c35fe5e128609bb93350245.jpg"));
        if (result) {
            email.setStatus("发送成功");
        } else {
            email.setStatus("发送失败");
        }
        email.setId(IdUtil.simpleUUID());
        email.setCreateTime(new Date());
        sysEmailMapper.insert(email);
    }
}




