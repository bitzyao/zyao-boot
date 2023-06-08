package com.zyao.common.api.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zyao
 * @version 1.0
 * @date 2023/6/7 11:29
 * @Description
 */
@Component
@ConfigurationProperties(prefix = "example")
public class Example {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Example{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
