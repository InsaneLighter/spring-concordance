package com.huang.controller;

import com.huang.producer.CustomizeProducer;
import com.huang.producer.DefinedKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @Time 2022-01-25 9:27
 * Created by Huang
 * className: CustomizeController
 * Description:
 */
@RestController
@RequestMapping
public class CustomizeController {
    //@Autowired
    private CustomizeProducer producer;
    @Autowired
    private DefinedKafkaProducer definedKafkaProducer;

    @RequestMapping("/send/v1/{message}")
    public String sendV1(@PathVariable String message) throws ExecutionException, InterruptedException {
        producer.send("topic","key",message);
        return "ok";
    }

    @RequestMapping("/send/v2/{message}")
    public String sendV2(@PathVariable String message) throws ExecutionException, InterruptedException {
        definedKafkaProducer.sendMessage(message);
        return "ok";
    }
}
