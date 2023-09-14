package com.zyao.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.zyao.common.aspect.annotation.MyCacheable;
import com.zyao.controller.BaseController;
import com.zyao.modal.sys.SysUser;
import com.zyao.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/get")
    @MyCacheable(folder = "sysUser",expireSeconds = 60,isArgAsKey = true)
    public SysUser getSysUserById(Long id){
        return sysUserService.getById(id);
    }

    @RequestMapping("/query")
    public ResponseEntity<?> queryUser(@RequestBody JSONObject filter){
        List<SysUser> sysUsers = sysUserService.list();
        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<SysUser> data = new PageImpl<>(sysUsers, pageable, sysUsers.size());
        return getResponse(data);
    }

    @RequestMapping("/list")
    public List<SysUser> listUser(@RequestBody JSONObject filter){
        List<SysUser> sysUsers = sysUserService.list();
        return sysUsers;
    }
}
