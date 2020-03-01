package com.gilxyj.openfeign;

import com.gilxyj.commons.User;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MultiHashtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-25 22:46
 **/
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() throws Exception {
        String s1 = helloService.hello2("肖大大");
        System.out.println(s1);

        User user = new User();
        user.setId(1);
        user.setUsername("高大大");
        User user1 = helloService.addUser2(user);
        System.out.println(user1);

        LinkedMultiValueMap<String, Object> userMap = new LinkedMultiValueMap<>();
        userMap.add("id", 222);
        userMap.add("username","龙大大");
        userMap.add("password","good");

        helloService.updateUser(userMap);

        helloService.deleteUser2(1);

        helloService.getUserByName(URLEncoder.encode("学习Java", "UTF-8"));

        return helloService.hello();
    }
}
