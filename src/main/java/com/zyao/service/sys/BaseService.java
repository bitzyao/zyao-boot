package com.zyao.service.sys;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/9/14 16:47
 * @Description
 */
public interface BaseService {
    // TODO 了解方法修饰符的作用域 default public protect private
     default Page getPage(JSONObject filter) {
        long page = filter.containsKey("page") ? filter.getLongValue("page")  : 1;
        long per = filter.containsKey("per") ? filter.getLongValue("per")  : 1000;
        return new Page(page, per);
    }
}
