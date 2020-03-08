package com.gilxyj.provider;

import com.gilxyj.api.IUserService;
import com.gilxyj.commons.User;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-09 22:07
 **/
@RestController
public class HelloController implements IUserService {

    @Value("${server.port}")
    Integer port;

    @PutMapping("/user1")
    public void updateUser1(User user){
        System.out.println(user);
    }

    @Override
    @RateLimiter(name = "rlA")
    public String hello(){
        String s = "hello javaboy:" + port;
        System.out.println("---->"+new Date());
        return s;
    }

    @Override
    public String hello2(String name){
        System.out.println(new Date()+":"+name);
        return "getHello"+name;
    }

    @PostMapping("/user1")
    public User addUser1(User user){
        return user;
    }

    @Override
    public User addUser2(@RequestBody User user){
        return user;
    }

    @PutMapping("/user2")
    public void updateUser2(@RequestBody User user){
        System.out.println(user);
    }

    @DeleteMapping("/user1")
    public void deleteUser1(Integer id){
        System.out.println(id);
    }

    @Override
    public void deleteUser2(@PathVariable Integer id){
        System.out.println(id);
    }

    @Override
    public void getUserByName(@RequestHeader String name) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode(name,"UTF-8"));
    }
}
