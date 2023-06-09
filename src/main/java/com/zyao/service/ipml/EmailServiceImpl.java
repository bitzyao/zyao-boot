package com.zyao.service.ipml;

import cn.hutool.core.util.IdUtil;
import com.zyao.common.api.sys.Email;
import com.zyao.common.utils.EmailUtil;
import com.zyao.mapper.sys.EmailMapper;
import com.zyao.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

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
    public void sendEmail(Email email) {

        Boolean result = emailUtil.sendEmail(email,new File("C:/Users\\ayao\\Pictures\\Screenshots\\8ecc6cd4bc7e8a858197cbc104fba744.jpeg"),new File("C:/Users\\ayao\\Pictures\\Screenshots\\8ecc6cd4bc7e8a858197cbc104fba744.jpeg"),new File("C:/Users\\ayao\\Pictures\\Screenshots\\8ecc6cd4bc7e8a858197cbc104fba744.jpeg"));
        if (result) {
            email.setStatus("发送成功");
        } else {
            email.setStatus("发送失败");
        }
        emailMapper.insert(email);
    }
}
