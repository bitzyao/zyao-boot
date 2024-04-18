package com.zyao.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

@UtilityClass  // 注解用于创建一个具有静态方法的实用工具类，而无需显式编写类的构造函数或将类声明为 final。这个注解通常用于声明不可实例化的工具类。
public class TenantContextHolder {
    /**

     支持父子线程数据传递
     */
    private final ThreadLocal THREAD_LOCAL_TENANT = new TransmittableThreadLocal<String>();
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
