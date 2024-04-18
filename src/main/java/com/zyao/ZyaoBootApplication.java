package com.zyao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
//import springfox.documentation.oas.annotations.EnableOpenApi;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@MapperScan("com/zyao/mapper")
public class ZyaoBootApplication {
    public static void main(String[] args) throws UnknownHostException {

        ConfigurableApplicationContext context =  SpringApplication.run(ZyaoBootApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        String port = environment.getProperty("server.port");
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println("启动完成，系统访问地址：http://localhost:" + port);
        System.out.println("启动完成，系统访问局域网地址：http://"+ip+":"+port);
        String springdocPath = environment.getProperty("springdoc.swagger-ui.path");
        String applicationName = environment.getProperty("spring.application.name");
        String contextPath = environment.getProperty("server.servlet.context-path");
        System.out.println("openAPI访问地址：http://"+ip+":"+port+contextPath+springdocPath);
    }
}
