package com.tingyun.springboot.restful.controller;

import com.tingyun.springboot.restful.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

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
    public User getUser() {
        User user = new User();
        user.setId(10l);
        user.setAge(20);
        user.setName("wade");
        user.setUsername("NBA");
        return user;
    }

    //restTemplate.postForObject方法
    @GetMapping("/user/post/{id}")
    public User setUser(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/info";
        User user = restTemplate.postForObject(url, null, User.class, id);
        return user;
    }

    @PostMapping("/user/info")
    public User getUserForPost() {
        User user = new User();
        user.setName("wade");
        user.setUsername("NBA");
        return user;
    }

    //restTemplat.put方法
    @GetMapping("/user/put/{id}")
    public void putUser(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/put/info";
        restTemplate.put(url, null, id);
    }

    //請求類型必須為put類型
    @PutMapping("/user/put/info")
    public void putUserInfo() {
        User user = new User();
        user.setName("wade");
        user.setUsername("NBA");
        System.err.println(user.toString());
    }


    //restTemplat.getForEntity()
    @GetMapping("/user/getForEntity/{id}")
    public String getForEntity(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/getForEntity/info";
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class, id);
        String body = (String) responseEntity.getBody();

        return body;
    }

    @GetMapping("/user/getForEntity/info")
    public String getResponseEntity() {
        User user = new User();
        user.setName("wade");
        user.setUsername("NBA");
        System.err.println(user.toString());
        return user.toString();
    }

    //restTemplate.postForEntity()
    @GetMapping("/user/postForEntity/{id}")
    public User postForEntity(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/postForEntity/info";
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, null, User.class, id);
        User user = responseEntity.getBody();
        return user;
    }

    @PostMapping("/user/postForEntity/info")
    public User postForEntity() {
        User user = new User();
        user.setId(10l);
        user.setName("wade");
        user.setUsername("NBA");
        user.setAge(20);
        System.err.println(user.toString());
        return user;
    }

    //restTemplate.postForLocation()
    @GetMapping("/user/postForLocation/{id}")
    public String postForLocation(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/postForLocation/info";
        URI uri = restTemplate.postForLocation(url, id);
        System.err.println(uri);
        if (uri == null) {
            return "failure";
        } else {
            return "success";
        }
    }

    @PostMapping("/user/postForLocation/info")
    public URI postForLocation() throws URISyntaxException {
        URI uri = new URI("http://127.0.0.1:7900/user/postForLocation/info");
        return uri;
    }

    //restTemplate.headForHeaders()
    @GetMapping("/user/headerForHeaders/{id}")
    public String headerForHeaders(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/headerForHeaders/info";
        HttpHeaders httpHeaders = restTemplate.headForHeaders(url, id);
        httpHeaders.add("java", "agent");
        return httpHeaders.toString();
    }

    @GetMapping("/user/headerForHeaders/info")
    public String headerForHeadersInfo() {
        User user = new User();
        user.setId(10l);
        user.setName("wade");
        user.setUsername("NBA");
        user.setAge(20);
        System.err.println(user.toString());
        return user.toString();
    }


    //restTemplate.delete()
    @GetMapping("/user/delete/{id}")
    public void delete(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/delete/info";
        restTemplate.delete(url, id);
    }

    @DeleteMapping("/user/delete/info")
    public void deleteInfo() {
        User user = new User();
        user.setId(10l);
        user.setName("wade");
        System.err.println(user);
    }

    //restTemplate.patchForObject()
    @GetMapping("/user/patchForObject/{id}")
    public String patchForObject(@PathVariable String id) {
        RestTemplate restTemplate1 = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectionRequestTimeout(5000);
        requestFactory.setConnectTimeout(5000);
        restTemplate1.setRequestFactory(requestFactory);
        String url = "http://127.0.0.1:7900/user/patchForObject/info";

        String result = restTemplate1.patchForObject(url, null, String.class, id);
        return result;
    }

    @PatchMapping("/user/patchForObject/info")
    public String patchForObject() {
        User user = new User();
        user.setId(10l);
        user.setName("wade");
        return user.toString();
    }

    //restTemplate.optionsForAllow()
    @GetMapping("/user/optionsForAllow/{id}")
    public Set<HttpMethod> optionsForAllow(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/optionsForAllow/info";
        Set<HttpMethod> setHttpMethod = restTemplate.optionsForAllow(url, id);
        return setHttpMethod;
    }

    @GetMapping("/user/optionsForAllow/info")
    public void optionsForAllowInfo() {
        User user = new User();
        user.setId(10l);
        user.setName("wade");
        System.err.println(user);
    }


    //restTemplate.exchange()
    @GetMapping("/user/exchange/{id}")
    public String exchange(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/exchange/info";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, String.class, id);
        return responseEntity.getBody();
    }

    @GetMapping("/user/exchange/info")
    public String exchagenInfo() {
        User user = new User();
        user.setId(10l);
        user.setName("wade");
        user.setAge(20);
        return user.toString();
    }

    //restTemplate.execute()
    @GetMapping("/user/execute/{id}")
    public String execute(@PathVariable String id){
        String url = "http://127.0.0.1:7900/user/execute/info";
        ResponseExtractor<String> responseExtractor=new ResponseExtractor<String>() {
            @Override
            public String extractData(ClientHttpResponse clientHttpResponse) throws IOException {
                return "hello,world";
            }
        };
        String result=restTemplate.execute(url,HttpMethod.GET, null,responseExtractor,id);
        return result;
    }
    @GetMapping("/user/execute/info")
    public String exexuteInfo(){
        return  "hello";
    }


}
