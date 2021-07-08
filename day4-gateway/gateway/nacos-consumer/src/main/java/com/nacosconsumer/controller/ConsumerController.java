package com.nacosconsumer.controller;

import com.nacosconsumer.feign.feign.ProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private ProviderFeign providerFeign;

    @GetMapping("hi")
    public String hi() {
        System.out.println(System.currentTimeMillis());
        return this.providerFeign.hello();
    }
}
