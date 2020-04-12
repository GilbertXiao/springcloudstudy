package com.gilxyj.zipkin02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-11 22:34
 **/
@RestController
public class HelloController {

    private static final Logger LOGGER= LoggerFactory.getLogger(HelloController.class);

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public void hello(){

        String forObject = restTemplate.getForObject("http://localhost:8080/hello?name={1}", String.class,"gilxyj");
        LOGGER.info("hello zipkin2 "+forObject);
    }

}
