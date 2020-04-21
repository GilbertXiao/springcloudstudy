package com.gilxyj.nacos02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-15 03:52
 **/
@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(){
        String forObject = restTemplate.getForObject("http://nacos01/hello", String.class);
        return forObject;
    }

}
