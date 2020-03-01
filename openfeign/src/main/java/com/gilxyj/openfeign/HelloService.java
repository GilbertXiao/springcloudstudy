package com.gilxyj.openfeign;

import com.gilxyj.api.IUserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "provider",fallbackFactory = HelloServiceFallbackFactory.class)
public interface HelloService extends IUserService {



    /*@GetMapping("/hello")
    String hello();//这里的方法名无所谓，随意取

    @GetMapping("/getHello")
    String hello2(@RequestParam("name") String name);

    @PostMapping("/user2")
    User addUser(@RequestBody User user);



    @DeleteMapping("/user2/{id}")
    void deleteUser(@PathVariable("id") Integer id);

    @GetMapping("/user")
    void getUserByName(@RequestHeader("name") String name);*/

    @PutMapping("/user1")
    void updateUser(MultiValueMap<String,Object> user);
}
