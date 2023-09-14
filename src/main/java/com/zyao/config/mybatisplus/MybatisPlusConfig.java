package com.zyao.config.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import lombok.AllArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

//@Configuration
//@AllArgsConstructor
//@MapperScan("com.zyao.mapper")
@Configuration
@AllArgsConstructor
@AutoConfigureBefore(MybatisPlusConfig.class)
public class MybatisPlusConfig {

    /**
     * 新多租户插件配置,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存万一出现问题
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();



        //动态表名插件 TODO 待完成
//        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
//        dynamicTableNameInnerInterceptor.setTableNameHandlerMap();
//
//        dynamicTableNameInnerInterceptor.setTableNameHandler((sql, tableName) -> {
//            String tenantId = TenantContextHolder.getTenantId();
//            //符合的表名拼接租户号
//            if (tenantProperties.getDynamicTables().stream().anyMatch(
//                    (t) -> t.equalsIgnoreCase(tableName))) {
//                return tableName + StringPool.UNDER_LINE + tenantId;
//            }
//            return tableName;
//        });
//        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);



        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new MyTenantHandler())); // 插入插件
        // 如果用了分页插件注意先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
        // 用了分页插件必须设置 MybatisConfiguration#useDeprecatedExecutor = false
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

    //动态表名插件


//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return configuration -> configuration.setUseDeprecatedExecutor(false);
//    }
}
