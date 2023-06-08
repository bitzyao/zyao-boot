package com.zyao.controller;

import com.zyao.common.api.pojo.Example;
import com.zyao.common.aspect.annotation.AutoLog;
import com.zyao.common.aspect.annotation.MyCacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/7 11:02
 * @Description 例子
 */
@RestController()
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private Example example;

    @AutoLog(value = "aa")
    @MyCacheable(key = "",folder = "dict",expireSeconds = 120)
    @RequestMapping("/index")
    public Example index(){
        return example;
    }
}
