package com.changwu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Changwu
 * @Date: 2019/11/29 21:08
 */
@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
public class ZuulApp  {
    public static void main(String[] args)  {
        SpringApplication.run(ZuulApp.class);
    }
}
