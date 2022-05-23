package com.huang.controller;

import com.alibaba.fastjson.JSONObject;
import com.huang.client.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time 2022-05-23 17:17
 * Created by Huang
 * className: DemoController
 * Description:
 */
@RestController
@RequestMapping("/service02")
public class DemoController {
    @Autowired
    ServiceClient serviceClient;

    @GetMapping("/serviceCall")
    public JSONObject testCall(){
        return serviceClient.testCall();
    };
}
