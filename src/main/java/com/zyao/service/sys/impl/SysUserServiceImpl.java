package com.zyao.service.sys.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyao.mapper.sys.SysUserMapper;
import com.zyao.modal.sys.SysUser;
import com.zyao.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ayao
 * @description 针对表【sys_user】的数据库操作Service实现
 * @createDate 2023-09-03 15:23:54
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Page<SysUser> pageQuery(JSONObject filter) {
        Page page = getPage(filter);
        return sysUserMapper.pageQuery(page,filter);
    }
}




