package com.zyao;

import com.zyao.modal.sys.SysUser;
import com.zyao.service.sys.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private SysUserService sysUserService;
    @Test
    void getUser() {

        SysUser sysUser = sysUserService.getById(1);
        System.out.println(sysUser.toString());
    }
}
