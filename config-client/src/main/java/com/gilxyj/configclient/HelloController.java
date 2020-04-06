package com.gilxyj.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-02 23:18
 **/
@RestController
@RefreshScope
public class HelloController {

    @Value("${gilxyj}")
    String str;

    @GetMapping("/hello")
    public String hello(){
        return str;
    }
}
