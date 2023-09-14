package com.zyao;

import com.zyao.mapper.sys.SysAccountMapper;
import com.zyao.modal.sys.SysAccount;
import com.zyao.service.sys.SysAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountTest {
    @Autowired
    private SysAccountService sysAccountService;

    @Autowired
    private SysAccountMapper sysAccountMapper;

    @Test
    void getAccount() {
        SysAccount sysAccount = sysAccountService.getById(1);
        System.out.println(sysAccount.toString());

        SysAccount sysAccount1 = sysAccountMapper.selectById(1);
        System.out.println(sysAccount1.toString());

    }
}
