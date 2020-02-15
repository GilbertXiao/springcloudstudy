package com.gilxyj.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilxyj.commons.User;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springcloudstudy
 * @description:
 * @author: GilbertXiao
 * @create: 2020-02-10 17:45
 **/
@RestController
public class UseHelloController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    @Qualifier("restTemplateOne")
    RestTemplate restTemplate;

    @Autowired
    @Qualifier("restTemplateTwo")
    RestTemplate restTemplateTwo;

    int count=0;

    /*@GetMapping("/useHello3")
    public String useHello3(){
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance serviceInstance = list.get((count++)%list.size());
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        StringBuffer sb= new StringBuffer();
        sb.append("http://").append(host).append(":").append(port).append("/hello");
        String s = restTemplate.getForObject(sb.toString(), String.class);
        return s;
    }*/

    @GetMapping("/useHello3")
    public String useHello3(){
        return restTemplateTwo.getForObject("http://provider/hello", String.class);
    }

    @GetMapping("/getTest")
    public void getHello() throws Exception{
        String forObject = restTemplateTwo.getForObject("http://provider/getHello?name={1}", String.class,"get object gilbertxiao1");
        System.out.println(forObject);

        ResponseEntity<String> responseEntity = restTemplateTwo.getForEntity("http://provider/getHello?name={1}", String.class, "get entity gilbertxiao2");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(responseEntity);
        System.out.println(s);


    }

    @GetMapping("/getTest2")
    public void getHello2() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String s =null;
        ResponseEntity<String> responseEntity=null;

        responseEntity = restTemplateTwo.getForEntity("http://provider/getHello?name={1}", String.class, "get entity 肖大大");
        s = objectMapper.writeValueAsString(responseEntity);
        System.out.println(s);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "get entity 高大大");
        responseEntity = restTemplateTwo.getForEntity("http://provider/getHello?name={name}", String.class, map);
        s = objectMapper.writeValueAsString(responseEntity);
        System.out.println(s);

        String url="http://provider/getHello?name="+ URLEncoder.encode("get entity 肖高大大","UTF-8");
        URI uri = URI.create(url);
        responseEntity = restTemplateTwo.getForEntity(uri, String.class);
        s = objectMapper.writeValueAsString(responseEntity);
        System.out.println(s);

    }



    @GetMapping("/useHello2")
    public String useHello2(){
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance serviceInstance = list.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        StringBuffer sb= new StringBuffer();
        sb.append("http://").append(host).append(":").append(port).append("/hello");
        HttpURLConnection conn=null;
        try {
            URL url = new URL(sb.toString());
            conn=(HttpURLConnection) url.openConnection();
            if (conn.getResponseCode()==200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("/useHello")
    public String useHello(){
        HttpURLConnection conn=null;
        try {
            URL url = new URL("http://localhost:1113/hello");
            conn=(HttpURLConnection) url.openConnection();
            if (conn.getResponseCode()==200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("/addUser1")
    public void addUser1() throws JsonProcessingException {
        //LinkedMultiValueMap 相同的key，多个value
        MultiValueMap<String,Object> map =new LinkedMultiValueMap<>();
        map.add("username", "肖大大");
        map.add("password", "1234");
        map.add("id",123);
        ResponseEntity<User> userResponseEntity = restTemplateTwo.postForEntity("http://provider/user1", map, User.class);

        ObjectMapper mapper=new ObjectMapper();
        String s = mapper.writeValueAsString(userResponseEntity);
        System.out.println(s);

    }

    @GetMapping("/addUser2")
    public void addUser2() throws JsonProcessingException {

        User user = new User();
        user.setUsername("高大大");
        user.setPassword("123|");
        user.setId(456);

        ResponseEntity<User> userResponseEntity = restTemplateTwo.postForEntity("http://provider/user2", user, User.class);

        ObjectMapper mapper=new ObjectMapper();
        String s = mapper.writeValueAsString(userResponseEntity);
        System.out.println(s);

    }

    @GetMapping("/reg")
    public void reg() throws JsonProcessingException {

        //LinkedMultiValueMap 相同的key，多个value
        MultiValueMap<String,Object> map =new LinkedMultiValueMap<>();
        map.add("username", "肖大大");
        map.add("password", "1234");
        map.add("id",123);
        URI uri = restTemplateTwo.postForLocation("http://provider/reg", map);

        ResponseEntity<String> forEntity = restTemplateTwo.getForEntity(uri, String.class);

        ObjectMapper mapper=new ObjectMapper();
        String s = mapper.writeValueAsString(forEntity);
        System.out.println(s);

    }

    @GetMapping("/updateUser1")
    public void updateUser1(){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "肖大大");
        map.add("password", "1234");
        map.add("id",123);
        restTemplateTwo.put("http://provider/user1", map);

    }

    @GetMapping("/updateUser2")
    public void updateUser2(){
        User user = new User();
        user.setUsername("高大大");
        user.setPassword("123|");
        user.setId(456);
        restTemplateTwo.put("http://provider/user2", user);
    }
}
