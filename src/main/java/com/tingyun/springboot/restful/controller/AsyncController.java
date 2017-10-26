package com.tingyun.springboot.restful.controller;

import org.asynchttpclient.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by tingyun on 2017/10/24.
 */

@RestController
public class AsyncController {


    @RequestMapping("/async")
    public String hello() throws ExecutionException, InterruptedException, IOException {
        AsyncHttpClient client=new DefaultAsyncHttpClient();
        String url="http://localhost:7900/async/test";
        ListenableFuture<Response> future=client.prepareGet(url).execute();
        Response response=future.get();
        return response.getResponseBody();
    }

    @RequestMapping(value = "/async/test",method = RequestMethod.GET)
    public String getHello(){
        return "hello";
    }
}
