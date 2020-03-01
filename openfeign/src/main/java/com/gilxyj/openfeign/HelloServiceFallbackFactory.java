package com.gilxyj.openfeign;

import com.gilxyj.commons.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-03-01 21:17
 **/
@Component
public class HelloServiceFallbackFactory implements FallbackFactory<HelloService> {

    @Override
    public HelloService create(Throwable throwable) {
        return new HelloService() {
            @Override
            public void updateUser(MultiValueMap<String, Object> user) {

            }

            @Override
            public String hello() {
                return "error1---";
            }

            @Override
            public String hello2(String name) {
                return "error2---";
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
        };
    }
}
