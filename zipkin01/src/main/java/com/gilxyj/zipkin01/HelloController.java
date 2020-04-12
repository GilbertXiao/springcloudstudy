package com.gilxyj.zipkin01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-11 22:22
 **/
@RestController
public class HelloController {

    private static final Logger LOGGER= LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello(String name){
        LOGGER.info("zipkin01-hello");
        return "hello "+name+"！";
    }
}
