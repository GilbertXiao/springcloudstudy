package com.gilxyj.consulconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-22 15:19
 **/
@RestController
public class HelloController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() throws JsonProcessingException {
        ServiceInstance choose = loadBalancerClient.choose("consul-provider");
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(choose);
        System.out.println(s);

        String forObject = restTemplate.getForObject(choose.getUri()+"/hello", String.class);
        return forObject;
    }

}
