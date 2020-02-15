package com.gilxyj.provider;

import com.gilxyj.commons.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-09 22:07
 **/
@RestController
public class HelloController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello")
    public String hello(){
        return "hello javaboy:"+port;
    }

    @GetMapping("/getHello")
    public String getHello(String name){
        return "getHello"+name;
    }

    @PostMapping("/user1")
    public User addUser1(User user){
        return user;
    }

    @PostMapping("/user2")
    public User addUser2(@RequestBody User user){
        return user;
    }

    @PutMapping("/user1")
    public void updateUser1(User user){
        System.out.println(user);
    }

    @PutMapping("/user2")
    public void updateUser2(@RequestBody User user){
        System.out.println(user);
    }

    @DeleteMapping("/deleteHello")
    public String deleteHello(String name){
        return "deleteHello"+name;
    }
}
