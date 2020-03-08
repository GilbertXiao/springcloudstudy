package com.gilxyj.resilience4j2;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-03-04 23:20
 **/
@Service
@CircuitBreaker(name="cbA",fallbackMethod = "error")
public class CirBreakerService {

    @Autowired
    RestTemplate restTemplate;

    public String hello(){
        return restTemplate.getForObject("http://localhost:1113/hello", String.class);
    }

    public String error(Throwable throwable){
        return "error";
    }


}
