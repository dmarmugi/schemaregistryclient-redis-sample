package com.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheableService {
    @Cacheable(cacheNames = "demo")
    public Demo getMessage(String payload){
        Demo thing = Demo.newBuilder().setMessage(payload).build();
        return thing;
    }
}
