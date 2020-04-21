package com.gilxyj.sentinel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootTest
class SentinelApplicationTests {

    @Test
    void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 15; i++) {
            String forEntity = restTemplate.getForObject("http://localhost:8086/hello", String.class);
            System.out.println(forEntity+":"+new Date());
        }
    }

}
