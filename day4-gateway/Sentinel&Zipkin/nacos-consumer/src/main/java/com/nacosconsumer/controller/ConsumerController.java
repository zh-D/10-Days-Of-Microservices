package com.nacosconsumer.controller;

import com.nacosconsumer.feign.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Qualifier("providerFallback")
    @Autowired
    private ProviderClient providerClient;

    @GetMapping("hi")
    public String hi() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());
        return this.providerClient.hello();
    }

    @GetMapping("hi2")
    public String hi2() {
        return this.providerClient.hello() + "hi2";
    }
}
