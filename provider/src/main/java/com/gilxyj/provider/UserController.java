package com.gilxyj.provider;

import com.gilxyj.commons.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-23 23:24
 **/
@RestController
public class UserController {

    @GetMapping("/user/{ids}")//假设consumer传过来的多个id的格式是1,2,3,4...
    public List<User> getUserbyIds(@PathVariable String ids){
        String[] split = ids.split(",");
        List<User> list=new ArrayList<>();
        for (String s : split) {
            User user = new User();
            user.setId(Integer.valueOf(s));
            user.setUsername("gilxyj"+s);
            list.add(user);

        }
        return list;
    }
}
