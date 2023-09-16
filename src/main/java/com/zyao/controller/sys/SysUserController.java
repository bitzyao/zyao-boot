package com.zyao.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyao.common.aspect.annotation.MyCacheable;
import com.zyao.controller.BaseController;
import com.zyao.modal.sys.SysUser;
import com.zyao.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api("User控制类")
@RestController
@RequestMapping("/User")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

//    @ApiOperation("通过id获取用户信息")
    @GetMapping("/get")
    @MyCacheable(folder = "sysUser",expireSeconds = 60,isArgAsKey = true)
    public SysUser getSysUserById(Long id){
        return sysUserService.getById(id);
    }

//    @ApiOperation("分页查询用户信息")
    @PostMapping("/query")
    public ResponseEntity<?> queryUser(@RequestBody JSONObject filter){
        Page<SysUser> sysUsers = sysUserService.pageQuery(filter);
        return getResponse(sysUsers);
    }
//    @ApiOperation("列表查询用户信息")
    @PostMapping ("/list")
    public List<SysUser> listUser(@RequestBody JSONObject filter){
        return sysUserService.list();
    }

//    @ApiOperation("添加用户信息")
    @PostMapping({"add"})
    public SysUser addUser(@RequestBody SysUser sysUser){
        sysUserService.save(sysUser);
        return sysUser;
    }
//    @ApiOperation("更新用户信息")
    @PutMapping ("update")
    public SysUser updateUser(@RequestBody SysUser sysUser){
        sysUserService.updateById(sysUser);
        return sysUser;
    }
//    @ApiOperation("删除用户信息")
    @DeleteMapping({"remove"})
    public void removeUser(Long id) {
        this.sysUserService.removeById(id);
    }

}
