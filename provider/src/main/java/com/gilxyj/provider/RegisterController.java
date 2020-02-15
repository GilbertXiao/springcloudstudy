package com.gilxyj.provider;

import com.gilxyj.commons.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @program: springcloudstudy
 * @description: register
 * @author: GilbertXiao
 * @create: 2020-02-15 06:25
 **/
@Controller
public class RegisterController {

    @PostMapping("/reg")
    public String register(User user) throws UnsupportedEncodingException {
        return "redirect:http://provider/login?username="+ URLEncoder.encode(user.getUsername(),"UTF-8");
    }

    @GetMapping("/login")
    @ResponseBody
    public String login(String username){
        return "login:"+username;
    }
}
