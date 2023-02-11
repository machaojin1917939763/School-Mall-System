package com.machaojin;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author machaojin
 * SpringBootApplication(exclude = {SecurityAutoConfiguration.class})关掉security
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients("com.machaojin.feign")
public class ProductApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
