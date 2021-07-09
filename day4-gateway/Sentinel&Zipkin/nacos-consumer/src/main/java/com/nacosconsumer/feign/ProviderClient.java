package com.nacosconsumer.feign;

import com.nacosconsumer.fallback.ProviderFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "nacos-provider")
public interface ProviderClient {

    @RequestMapping("hello")
    public String hello();
}
