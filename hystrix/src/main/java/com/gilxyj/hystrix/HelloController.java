package com.gilxyj.hystrix;

import com.gilxyj.commons.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-23 15:01
 **/
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(){
        return helloService.hello();
    }

    @GetMapping("/hello2")
    public void hello2(){
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();

        HelloCommand helloCommand = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("gilxyj")), restTemplate,"gilxyj");
        /*helloCommand.run();//不能这样写*/
        String execute = helloCommand.execute();
        System.out.println(execute);

        HelloCommand helloCommand2 = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("gilxyj")), restTemplate,"gilxyj");
        Future<String> queue = helloCommand2.queue();
        try {
            String s = queue.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        hystrixRequestContext.close();

    }

    @GetMapping("/hello3")
    public String hello3(){
        Future<String> stringFuture = helloService.hello2();
        String s = null;
        try {
            s = stringFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return s;
    }

    @GetMapping("/hello4")
    public void hello4(){
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        //第一次的时候，数据已经缓存下来了
        String s1 = helloService.hello3("gilxyj");
        //删除数据,同时缓存中的数据也会被删除
        helloService.deleteUserByName("gilxyj");
        //由于缓存数据被删除，所以重新向provider请求数据
        String s2 = helloService.hello3("gilxyj");
        hystrixRequestContext.close();
    }

    @Autowired
    UserService userService;

    @GetMapping("/hello5")
    public void hello5() throws ExecutionException, InterruptedException {
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        UserCollapseCommand userCollapseCommand1 = new UserCollapseCommand(userService, 99);
        UserCollapseCommand userCollapseCommand2 = new UserCollapseCommand(userService, 98);
        UserCollapseCommand userCollapseCommand3 = new UserCollapseCommand(userService, 97);
        UserCollapseCommand userCollapseCommand4 = new UserCollapseCommand(userService, 96);

        Future<User> queue1 = userCollapseCommand1.queue();
        Future<User> queue2 = userCollapseCommand2.queue();
        Future<User> queue3 = userCollapseCommand3.queue();
        Future<User> queue4 = userCollapseCommand4.queue();

        User user1 = queue1.get();
        User user2 = queue2.get();
        User user3 = queue3.get();
        User user4 = queue4.get();

        Thread.sleep(2000);

        UserCollapseCommand userCollapseCommand5 = new UserCollapseCommand(userService, 95);
        Future<User> queue5 = userCollapseCommand5.queue();
        User user5 = queue5.get();
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        System.out.println(user5);
        hystrixRequestContext.close();
    }

    @GetMapping("/hello6")
    public void hello6() throws ExecutionException, InterruptedException {
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        Future<User> queue1 = userService.getUserById(99);
        Future<User> queue2 = userService.getUserById(98);
        Future<User> queue3 = userService.getUserById(97);
        Future<User> queue4 = userService.getUserById(96);

        User user1 = queue1.get();
        User user2 = queue2.get();
        User user3 = queue3.get();
        User user4 = queue4.get();

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        hystrixRequestContext.close();

    }
}
