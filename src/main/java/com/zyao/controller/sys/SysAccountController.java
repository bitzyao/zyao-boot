package com.zyao.controller.sys;

import com.zyao.controller.BaseController;
import com.zyao.modal.sys.SysAccount;
import com.zyao.service.sys.SysAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Account")
public class SysAccountController extends BaseController {

    @Autowired
    private SysAccountService sysAccountService;

    @GetMapping("/get")
    public SysAccount getAccountById(Long id){
        return this.sysAccountService.getById(id);
    }
}
