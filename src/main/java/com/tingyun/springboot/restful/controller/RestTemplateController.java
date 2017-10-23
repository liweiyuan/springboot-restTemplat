package com.tingyun.springboot.restful.controller;

import com.tingyun.springboot.restful.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.*;
import org.springframework.web.bind.annotation.*;
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
//@Controller
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;


    //get方法
    //@GetMapping("/get/user/{id}")
    @RequestMapping(value = "/get/user/{id}", method = RequestMethod.GET)
    public User getUserId(@PathVariable Long id) {
        User u = this.restTemplate.getForObject("http://localhost:7900/user/id/" + id,
                User.class);
        System.out.println(u);
        return u;
    }


    //模拟一个查询数据库的操作
    //@GetMapping("/user/id/{id}")
    @RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
    public User getUser() {
        User user = new User();
        user.setId(10l);
        user.setAge(20);
        user.setName("wade");
        user.setUsername("NBA");
        return user;
    }

    //restTemplate.postForObject方法
    //@GetMapping("/user/post/{id}")
    @RequestMapping("/user/post/{id}")
    public User setUser(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/info/" + id;
        User user = restTemplate.postForObject(url, null, User.class, id);
        return user;
    }

    //@PostMapping("/user/info/{id}")
    @RequestMapping(value = "/user/info/{id}",method = RequestMethod.POST)
    public User getUserForPost() {
        User user = new User();
        user.setName("wade");
        user.setUsername("NBA");
        return user;
    }

    //restTemplat.put方法
    //@GetMapping("/user/put/{id}")
    @RequestMapping(value = "/user/put/{id}",method = RequestMethod.GET)
    public void putUser(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/put/info/" + id;
        restTemplate.put(url, null, id);
    }

    //請求類型必須為put類型
    //@PutMapping("/user/put/info/{id}")
    @RequestMapping(value = "/user/put/info/{id}",method = RequestMethod.PUT)
    public void putUserInfo() {
        User user = new User();
        user.setName("wade");
        user.setUsername("NBA");
        System.err.println(user.toString());
    }


    //restTemplat.getForEntity()
    //@GetMapping("/user/getForEntity/{id}")
    @RequestMapping(value = "/user/getForEntity/{id}",method = RequestMethod.GET)
    public String getForEntity(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/getForEntity/info/" + id;
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class, id);
        String body = (String) responseEntity.getBody();

        return body;
    }

    //@GetMapping("/user/getForEntity/info/{id}")
    @RequestMapping(value = "/user/getForEntity/info/{id}",method = RequestMethod.GET)
    public String getResponseEntity() {
        User user = new User();
        user.setName("wade");
        user.setUsername("NBA");
        System.err.println(user.toString());
        return user.toString();
    }

    //restTemplate.postForEntity()
    //@GetMapping("/user/postForEntity/{id}")
    @RequestMapping(value = "/user/postForEntity/{id}",method = RequestMethod.GET)
    public User postForEntity(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/postForEntity/info/" + id;
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, null, User.class, id);
        User user = responseEntity.getBody();
        return user;
    }

    //@PostMapping("/user/postForEntity/info/{id}")
    @RequestMapping(value = "/user/postForEntity/info/{id}",method = RequestMethod.POST)
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
    //@GetMapping("/user/postForLocation/{id}")
    @RequestMapping("/user/postForLocation/{id}")
    public String postForLocation(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/postForLocation/info/" + id;
        URI uri = restTemplate.postForLocation(url, id);
        System.err.println(uri);
        if (uri == null) {
            return "failure";
        } else {
            return "success";
        }
    }

    //@PostMapping("/user/postForLocation/info/{id}")
    @RequestMapping(value = "/user/postForLocation/info/{id}",method = RequestMethod.POST)
    public URI postForLocation() throws URISyntaxException {
        URI uri = new URI("http://127.0.0.1:7900/user/postForLocation/info");
        return uri;
    }

    //restTemplate.headForHeaders()
    //@GetMapping("/user/headerForHeaders/{id}")
    @RequestMapping(value = "/user/headerForHeaders/{id}",method = RequestMethod.GET)
    public String headerForHeaders(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/headerForHeaders/info/" + id;
        HttpHeaders httpHeaders = restTemplate.headForHeaders(url, id);
        httpHeaders.add("java", "agent");
        return httpHeaders.toString();
    }

    //@GetMapping("/user/headerForHeaders/info/{id}")
    @RequestMapping("/user/headerForHeaders/info/{id}")
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
    //@GetMapping("/user/delete/{id}")
    @RequestMapping("/user/delete/{id}")
    public void delete(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/delete/info/" + id;
        restTemplate.delete(url, id);
    }

    //@DeleteMapping("/user/delete/info/{id}")
    @RequestMapping(value = "/user/delete/info/{id}",method = RequestMethod.DELETE)
    public void deleteInfo() {
        User user = new User();
        user.setId(10l);
        user.setName("wade");
        System.err.println(user);
    }


    //restTemplate.optionsForAllow()
    //@GetMapping("/user/optionsForAllow/{id}")
    @RequestMapping(value = "/user/optionsForAllow/{id}",method = RequestMethod.GET)
    public Set<HttpMethod> optionsForAllow(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/optionsForAllow/info/" + id;
        Set<HttpMethod> setHttpMethod = restTemplate.optionsForAllow(url, id);
        return setHttpMethod;
    }

    //@GetMapping("/user/optionsForAllow/info/{id}")
    @RequestMapping(value = "/user/optionsForAllow/info/{id}",method = RequestMethod.GET)
    public void optionsForAllowInfo() {
        User user = new User();
        user.setId(10l);
        user.setName("wade");
        System.err.println(user);
    }


    //restTemplate.exchange()
    //@GetMapping("/user/exchange/{id}")
    @RequestMapping(value = "/user/exchange/{id}",method = RequestMethod.GET)
    public String exchange(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/exchange/info/" + id;
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, String.class, id);
        return responseEntity.getBody();
    }

    //@GetMapping("/user/exchange/info/{id}")
    @RequestMapping("/user/exchange/info/{id}")
    public String exchagenInfo() {
        User user = new User();
        user.setId(10l);
        user.setName("wade");
        user.setAge(20);
        return user.toString();
    }

    //restTemplate.execute()
    //
    // @GetMapping("/user/execute/{id}")
    @RequestMapping("/user/execute/{id}")
    public String execute(@PathVariable String id) {
        String url = "http://127.0.0.1:7900/user/execute/info/" + id;
        ResponseExtractor<String> responseExtractor = new ResponseExtractor<String>() {
            @Override
            public String extractData(ClientHttpResponse clientHttpResponse) throws IOException {
                return "hello,world";
            }
        };
        String result = restTemplate.execute(url, HttpMethod.GET, null, responseExtractor, id);
        return result;
    }

    //@GetMapping("/user/execute/info/{id}")
    @RequestMapping("/user/execute/info/{id}")
    public String exexuteInfo() {
        return "hello";
    }

    //restTemplate.patchForObject()
    //@GetMapping("/user/patchForObject/{id}")
    @RequestMapping("/user/patchForObject/{id}")
    public String patchForObject(@PathVariable String id) {
        RestTemplate restTemplate1 = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectionRequestTimeout(5000);
        requestFactory.setConnectTimeout(5000);
        restTemplate1.setRequestFactory(requestFactory);
        String url = "http://127.0.0.1:7900/user/patchForObject/info/" + id;

        String result = restTemplate1.patchForObject(url, null, String.class, id);
        return result;
    }

    //@PatchMapping("/user/patchForObject/info/{id}")
    @RequestMapping(value = "/user/patchForObject/info/{id}",method = RequestMethod.PATCH)
    public String patchForObject() {
        User user = new User();
        user.setId(10l);
        user.setName("wade");
        return user.toString();
    }


    //netty支持
    //@GetMapping("/user/netty/{id}")
    @RequestMapping("/user/netty/{id}")
    public String netty(@PathVariable String id) {
        RestTemplate restTemplate1 = new RestTemplate();
        Netty4ClientHttpRequestFactory requestFactory = new Netty4ClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setMaxResponseSize(10000);
        restTemplate1.setRequestFactory(requestFactory);
        String url = "http://127.0.0.1:7900/user/netty/info/" + id;

        HttpHeaders httpHeaders = restTemplate1.headForHeaders(url);
        httpHeaders.add("java", "agent");
        return httpHeaders.toString();
    }

    //@GetMapping("/user/netty/info/{id}")
    @RequestMapping("/user/netty/info/{id}")
    public String nettyInfo(@PathVariable String id) {
        User user = new User();
        user.setId(10l);
        user.setName("wade");
        System.err.println(user.toString());
        return user.toString();
    }


    //okhttp
    //@GetMapping("/user/okHttp/{id}")
    @RequestMapping("/user/okHttp/{id}")
    public String okHttp(@PathVariable String id) {
        RestTemplate restTemplate1 = new RestTemplate();
        OkHttpClientHttpRequestFactory requestFactory = new OkHttpClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setWriteTimeout(10000);
        restTemplate1.setRequestFactory(requestFactory);

        String url = "http://127.0.0.1:7900/user/okHttp/info/" + id;
        HttpHeaders httpHeaders = restTemplate1.headForHeaders(url);
        httpHeaders.add("java", "agent");
        return httpHeaders.toString();
    }

    //@GetMapping("/user/okHttp/info/{id}")
    @RequestMapping(value = "/user/okHttp/info/{id}",method = RequestMethod.GET)
    public String okHttpInfo(@PathVariable String id) {
        User user = new User();
        user.setId(Long.valueOf(id));
        user.setName("wade");
        System.err.println(user.toString());
        return user.toString();
    }


    //okHttp3
    //@GetMapping("/user/okHttp3/{id}")
    @RequestMapping("/user/okHttp3/{id}")
    public String okHttp3(@PathVariable String id) {
        RestTemplate restTemplate1 = new RestTemplate();
        OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);

        restTemplate1.setRequestFactory(requestFactory);
        String url = "http://127.0.0.1:7900/user/okHttp3/info/" + id;
        HttpHeaders httpHeaders = restTemplate1.headForHeaders(url);
        httpHeaders.add("java", "agent");
        return httpHeaders.toString();
    }

    //@GetMapping("/user/okHttp3/info/{id}")
    @RequestMapping("/user/okHttp3/info/{id}")
    public String okHttp3Info(@PathVariable String id) {
        System.err.println("id: " + id);
        User user = new User();
        user.setId(Long.valueOf(id));
        user.setUsername("wade");
        System.err.println(user.toString());
        return user.toString();
    }

}
