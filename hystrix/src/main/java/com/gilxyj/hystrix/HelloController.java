package com.gilxyj.hystrix;

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
}
