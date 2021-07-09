package com.nacosconsumer.fallback;

import com.nacosconsumer.feign.ProviderClient;
import org.springframework.stereotype.Component;

@Component
public class ProviderFallback implements ProviderClient {

    @Override
    public String hello() {
        return "现在服务器忙，请稍后再试！";
    }
}