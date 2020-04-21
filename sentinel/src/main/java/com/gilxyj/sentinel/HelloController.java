package com.gilxyj.sentinel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-14 00:34
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello sentinal";
    }
}
