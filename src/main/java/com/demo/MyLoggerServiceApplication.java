package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.Payload;


@SpringBootApplication
@EnableBinding(Processor.class)
@EnableCaching
@EnableSchemaRegistryClient
public class MyLoggerServiceApplication {
    private CacheableService cacheableService;

    public static void main(String[] args) {
        SpringApplication.run(MyLoggerServiceApplication.class, args);
    }

    public MyLoggerServiceApplication(CacheableService cacheableService) {
        this.cacheableService = cacheableService;
    }

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public Demo enrichLogMessage(@Payload String log) {
        System.out.println(log);

        return cacheableService.getMessage(log);
    }
}
