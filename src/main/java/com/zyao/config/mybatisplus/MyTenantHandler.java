package com.zyao.config.mybatisplus;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.zyao.common.context.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.stereotype.Component;

/**
 * 租户维护处理器
 */
@Slf4j
@Component
public class MyTenantHandler implements TenantLineHandler {

    private final MyTenantConfigProperties myTenantConfigProperties;

    public MyTenantHandler(MyTenantConfigProperties myTenantConfigProperties) {
        this.myTenantConfigProperties = myTenantConfigProperties;
    }
    /**
     * 获取租户 ID 值表达式，只支持单个 ID 值
     * @return 租户 ID 值表达式
     */
    @Override
    public Expression getTenantId() {
        String tenantId = TenantContextHolder.getTenantId();
        log.info("当前租户为 >> {}", tenantId);
        if (StrUtil.isBlank(tenantId)) {
            return new NullValue(); // 数据库的值
        }
        return new StringValue(tenantId);
    }
    /**
     * 获取租户字段名
     * @return 租户字段名
     */
    @Override
    public String getTenantIdColumn() {
        return myTenantConfigProperties.getColumn();
    }
    /**
     * 根据表名判断是否忽略拼接多租户条件
     * <p>
     * 默认都要进行解析并拼接多租户条件
     * @param tableName 表名
     * @return 是否忽略, true:表示忽略，false:需要解析并拼接多租户条件
     */
    @Override
    public boolean ignoreTable(String tableName) {
        String tenantId = TenantContextHolder.getTenantId();
        // 租户中ID 为空，屏蔽查询，
        if (StrUtil.isBlank(tenantId)) {
            return Boolean.TRUE;
        }
        // 匹配不区分大小写
        return myTenantConfigProperties.getIgnoreTables().stream().anyMatch((t) -> tableName.toUpperCase().startsWith(t.toUpperCase()) || tableName.equalsIgnoreCase(t));
    }
}
