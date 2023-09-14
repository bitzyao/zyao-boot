package com.zyao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
// @MapperScan 扫描指定包下面的接口类，进而生成mybatis的代理类
// @MapperScan是会自动扫描包下面的接口类，类似会给每个接口类自动加上@Mapper。
@MapperScan("com/zyao/mapper")
public class ZyaoBootApplication {
    public static void main(String[] args) throws UnknownHostException {

        ConfigurableApplicationContext context =  SpringApplication.run(ZyaoBootApplication.class, args);

        Environment environment = context.getBean(Environment.class);
        String port = environment.getProperty("server.port");
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println("启动完成，系统访问地址：http://localhost:" + port);
        System.out.println("启动完成，系统访问局域网地址：http://"+ip+":"+port);
        System.out.println("Swagger访问地址：http://"+ip+":"+port+"/doc.html");
    }
}
