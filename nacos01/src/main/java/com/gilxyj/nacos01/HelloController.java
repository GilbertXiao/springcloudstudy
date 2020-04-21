package com.gilxyj.nacos01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-13 01:31
 **/
@RestController
public class HelloController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello")
    public String hello(){
        return "hello"+port;
    }
}
