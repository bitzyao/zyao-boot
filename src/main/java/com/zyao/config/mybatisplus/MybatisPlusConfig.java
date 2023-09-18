package com.zyao.config.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;

@Slf4j
@Configuration
@AllArgsConstructor
@AutoConfigureBefore(MybatisPlusConfig.class)
@EnableConfigurationProperties(MyTenantConfigProperties.class)
public class MybatisPlusConfig {
    /**
     * 新多租户插件配置,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存万一出现问题
     */
    @Autowired
    private MyTenantConfigProperties myTenantConfigProperties;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 简化写法 直接创建好实现类
        if(myTenantConfigProperties.getEnable()){ // 启用多租户插件
            log.info("已开启多租客模式");
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new MyTenantHandler(myTenantConfigProperties))); // 插入多租客插件插件
        }

        // 如果用了分页插件注意先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
        // 用了分页插件必须设置 MybatisConfiguration#useDeprecatedExecutor = false  // 已弃用
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor();
        // 设置数据库方言类型
        pageInterceptor.setDbType(DbType.MYSQL);
        // 下面配置根据需求自行设置
        // 设置请求的页面大于最大页后操作，true调回到首页，false继续请求。默认false
        pageInterceptor.setOverflow(false);
        // 单页分页条数限制，默认无限制
        pageInterceptor.setMaxLimit(500L);
        // 插入分页插件
        interceptor.addInnerInterceptor(pageInterceptor);
        return interceptor;
    }

    // 目前已废弃
//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return configuration -> configuration.setUseDeprecatedExecutor(false);
//    }
}
