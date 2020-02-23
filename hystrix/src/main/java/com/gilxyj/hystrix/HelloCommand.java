package com.gilxyj.hystrix;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-23 16:15
 **/
public class HelloCommand extends HystrixCommand<String> {

    RestTemplate restTemplate;

    String name;

    public HelloCommand(Setter setter, RestTemplate restTemplate,String name) {
        super(setter);
        this.restTemplate = restTemplate;
        this.name=name;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://provider/getHello?name={1}", String.class,name);
    }

    @Override
    protected String getCacheKey() {
        return name;
    }

    /**
     * 这个方法就是请求失败的回调
     * @return
     */
    @Override
    protected String getFallback() {
        return "error-extends:"+getExecutionException().getMessage();
    }
}
