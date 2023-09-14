package com.zyao.mapper.sys;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyao.modal.sys.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author ayao
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2023-09-03 15:23:54
* @Entity com.zyao.modal.sys.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    Page<SysUser> pageQuery(Page page, @Param("filter") JSONObject filter);
}




