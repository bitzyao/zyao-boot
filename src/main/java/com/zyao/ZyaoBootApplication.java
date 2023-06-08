package com.zyao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @MapperScan 扫描指定包下面的接口类，进而生成mybatis的代理类
// @MapperScan是会自动扫描包下面的接口类，类似会给每个接口类自动加上@Mapper。
@MapperScan("com/zyao/mapper")
public class ZyaoBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZyaoBootApplication.class, args);
    }

}
