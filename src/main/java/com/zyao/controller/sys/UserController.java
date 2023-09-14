package com.zyao.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyao.common.aspect.annotation.MyCacheable;
import com.zyao.controller.BaseController;
import com.zyao.modal.sys.SysUser;
import com.zyao.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/query")
    public ResponseEntity<?> queryUser(@RequestBody JSONObject filter){
        Page<SysUser> sysUsers = sysUserService.pageQuery(filter);
        return getResponse(sysUsers);
    }

    @PostMapping ("/list")
    public List<SysUser> listUser(@RequestBody JSONObject filter){
        return sysUserService.list();
    }


    @PostMapping({"add"})
    public SysUser addUser(@RequestBody SysUser sysUser){
        sysUserService.save(sysUser);
        return sysUser;
    }

    @PutMapping ("update")
    public SysUser updateUser(@RequestBody SysUser sysUser){
        sysUserService.updateById(sysUser);
        return sysUser;
    }

    @DeleteMapping({"remove"})
    public void removeUser(Long id) {
        this.sysUserService.removeById(id);
    }

}
