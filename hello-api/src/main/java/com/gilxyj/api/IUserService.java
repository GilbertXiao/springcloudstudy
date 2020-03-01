package com.gilxyj.api;

import com.gilxyj.commons.User;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


public interface IUserService {

    @GetMapping("/hello")
    String hello();//这里的方法名无所谓，随意取

    @GetMapping("/getHello")
    String hello2(@RequestParam("name") String name);

    @PostMapping("/user2")
    User addUser2(@RequestBody User user);

//    @PutMapping("/user1")
//    void updateUser1(@RequestParam("user") MultiValueMap<String,Object> user);

    @DeleteMapping("/user2/{id}")
    void deleteUser2(@PathVariable("id") Integer id);

    @GetMapping("/user")
    void getUserByName(@RequestHeader("name") String name) throws Exception;
}
