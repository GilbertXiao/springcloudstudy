package com.gilxyj.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-13 01:11
 **/
@RestController
@RefreshScope//动态刷新
public class HelloController {

    @Value("${name}")
    String name;

    @GetMapping("/hello")
    public String hello(){
        return name;
    }

}
