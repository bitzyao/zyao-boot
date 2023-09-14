package com.zyao.config.mybatisplus;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 多租户配置
 */
@Data
@Configuration
@EnableConfigurationProperties(MyTenantConfigProperties.class)
@ConfigurationProperties(prefix = "tenant") // 音色到配置文件的属性
public class MyTenantConfigProperties {
    /**
     * 多租户字段名称(根据实际项目修改)
     */
    private String column = "tenant_id";

    /**
    是否开启租户模式
*/
    private Boolean enable = true;
    /**
     需要排除的多租户的表(根据自己需要修改)
     /
     private List ignoreTables = Arrays.asList(“t_menu”, “t_oauth_client”,“t_tenant_info”,“t_server_info”);
     /*
     动态表名的表(根据自己需要修改)
     */
    private List ignoreTables = Arrays.asList("t_opt_log", "t_flow");

    /**
     排除不进行租户隔离的sql
     样例全路径：com.cherf.system.sauth.mapper.AppAccessMapper.findList
     */
    private List ignoreSqls = new ArrayList<>();
}
