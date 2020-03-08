package com.gilxyj.resilience4j2;


import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-03-04 22:04
 **/
@Service
@Retry(name="retryA") //表示要使用的重试策略
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    public String hello(){
        for (int i = 0; i < 10 ; i++) {
             restTemplate.getForObject("http://localhost:1113/hello", String.class);
        }
        return "success";
    }

}
