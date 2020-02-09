package com.gilxyj.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-09 22:07
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello javaboy";
    }
}
