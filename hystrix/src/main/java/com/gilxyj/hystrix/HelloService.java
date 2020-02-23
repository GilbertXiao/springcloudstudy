package com.gilxyj.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-23 00:02
 **/
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 在这个方法中，我们将发出一个远程调用，去调用provider中提供的/hello接口
     *
     * 但是，整个调用可能会失败，
     *
     * 我们在这个方法上添加@HystrixCommand 注解，配置fallbackMethod 属性，这个属性表示该方法调用失败时的临时替代方法
     * @return
     */
    @HystrixCommand(fallbackMethod = "error")
    public String hello(){
        return restTemplate.getForObject("http://provider/hello", String.class);
    }

    /**
     * 注意，这个方法名字要和fallbackMethod一致
     *方法返回值也要和对应的方法一致
     * @return
     */
    public String error(){
        return "error";
    }

}
