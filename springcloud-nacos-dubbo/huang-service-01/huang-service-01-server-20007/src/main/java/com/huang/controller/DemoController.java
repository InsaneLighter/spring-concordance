package com.huang.controller;

import com.alibaba.fastjson.JSONObject;
import com.huang.service.Demo01Service;
import com.huang.service.Demo02Service;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time 2022-05-24 8:50
 * Created by Huang
 * className: DemoController
 * Description:
 */
@RestController
@RequestMapping
public class DemoController {
    @Autowired
    private Demo01Service demoService;
    @DubboReference
    private Demo02Service demo02Service;

    @GetMapping("/serviceCall")
    public JSONObject serviceCall(){
        return demoService.serviceCall();
    }

    @GetMapping("/service02/serviceCall")
    public JSONObject service02ServiceCall(){
        return demo02Service.serviceCall();
    }
}
