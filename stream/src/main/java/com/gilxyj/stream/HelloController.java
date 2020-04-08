package com.gilxyj.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-07 23:43
 **/
@RestController
public class HelloController {

    private static final Logger LOGGER= LoggerFactory.getLogger(HelloController.class);

    @Autowired
    MyChannel myChannel;

    @GetMapping("/hello")
    public void hello(){
        LOGGER.info("send msg："+new Date());
        myChannel.output().send(MessageBuilder.withPayload("hello spring cloud stream!").setHeader("x-delay",3000).build());
    }
}
