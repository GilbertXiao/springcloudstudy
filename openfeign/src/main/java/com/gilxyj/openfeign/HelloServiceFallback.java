package com.gilxyj.openfeign;

import com.gilxyj.commons.User;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-03-01 21:01
 **/
@Component
@RequestMapping("/gilxyj") //防止请求地址重复
public class HelloServiceFallback implements HelloService {

    @Override
    public void updateUser(MultiValueMap<String, Object> user) {

    }

    @Override
    public String hello() {
        return "error1";
    }

    @Override
    public String hello2(String name) {
        return "error2";
    }

    @Override
    public User addUser2(User user) {
        return null;
    }

    @Override
    public void deleteUser2(Integer id) {

    }

    @Override
    public void getUserByName(String name) throws Exception {

    }
}
