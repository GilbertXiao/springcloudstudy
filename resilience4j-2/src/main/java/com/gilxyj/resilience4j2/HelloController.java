package com.gilxyj.resilience4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-03-04 22:04
 **/
@RestController
public class HelloController {

    @Autowired
     HelloService helloService;

    @Autowired
    CirBreakerService cirBreakerService;

    @GetMapping("/hello")
    public String hello(){
        return helloService.hello();
    }

    @GetMapping("/hello1")
    public String hello1(){
        return cirBreakerService.hello();
    }
}
