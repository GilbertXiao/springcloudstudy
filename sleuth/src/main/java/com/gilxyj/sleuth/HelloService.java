package com.gilxyj.sleuth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-10 00:37
 **/
@Service
public class HelloService {

    private static final Log log = LogFactory.getLog(HelloService.class);

    @Async
    public String backgroundFun(){
        log.info("backgroundFun");
        return "hello4";
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void schedul1(){
        log.info("start:");
        backgroundFun();
        log.info("end:");
    }

}
