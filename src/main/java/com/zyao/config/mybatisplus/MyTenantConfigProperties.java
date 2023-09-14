package com.zyao.config.mybatisplus;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 多租户配置
 */

@Data
@Component
@ConfigurationProperties(prefix = "tenant") // 音色到配置文件的属性
public class MyTenantConfigProperties {
    /**
     * 多租户字段名称(根据实际项目修改)
     */
    private String column = "tenant_id";

    /**
    是否开启租户模式 默认不开启
    */
    private Boolean enable = Boolean.FALSE;

    /**
     需要忽略拼接多租户的表 (根据自己需要修改)
     */
    private List<String> ignoreTables = new ArrayList<>();

    /**
     *
     * TODO 开发中
     排除不进行租户隔离的sql
     样例全路径：com.cherf.system.sauth.mapper.AppAccessMapper.findList
     */
    private List<String> ignoreSqls = new ArrayList<>();

}
