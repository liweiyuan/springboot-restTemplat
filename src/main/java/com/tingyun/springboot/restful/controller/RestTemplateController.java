package com.tingyun.springboot.restful.controller;

import com.tingyun.springboot.restful.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tingyun on 2017/10/20.
 */
@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;


    //get方法
    @GetMapping("/get/user/{id}")
    public User getUserId(@PathVariable Long id) {
        User u = this.restTemplate.getForObject("http://localhost:7900/user/id",
                User.class);
        System.out.println(u);
        return u;
    }


    //模拟一个查询数据库的操作
    @GetMapping("/user/id")
    public User getUser(){
        User user=new User();
        user.setId(10l);
        user.setAge(20);
        user.setName("wade");
        user.setUsername("NBA");
        return user;
    }

    //restTemplate.postForObject方法
    @GetMapping("/user/post/{id}")
    public User  setUser(@PathVariable String  id){
        String url="http://127.0.0.1:7900/user/info";
        User user=restTemplate.postForObject(url,null,User.class,id);
        return user;
    }

    @PostMapping("/user/info")
    public User getUserForPost(){
        User user=new User();
        user.setName("wade");
        user.setUsername("NBA");
        return user;
    }
}
