package com.zyao.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

@UtilityClass  // TODO 学习这个注解的作用
public class TenantContextHolder {
    /**

     支持父子线程数据传递
     */
    private final ThreadLocal THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();
    /**

     设置租户ID
     @param tenantId 租户ID
     */
    public void setTenantId(String tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
    }
    /**

     获取租户ID
     @return String
     */
    public String getTenantId() {
        return (String) THREAD_LOCAL_TENANT.get();
    }
    /**

     清除tenantId
     */
    public void clear() {
        THREAD_LOCAL_TENANT.remove();
    }
}
