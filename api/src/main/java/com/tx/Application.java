package com.tx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author tx_li
 * @Date 2020/4/19 11:55
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.tx.mapper")
@ComponentScan(basePackages = {"com.tx", "org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
