package com.idme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={ "com.huawei.innovation","com.idme"})
public class IdmeApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdmeApplication.class, args);
    }

}
