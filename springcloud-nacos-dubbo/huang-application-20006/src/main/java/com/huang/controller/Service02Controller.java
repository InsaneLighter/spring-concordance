package com.huang.controller;

import com.alibaba.fastjson.JSONObject;
import com.huang.service.Demo02Service;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time 2022-05-24 9:10
 * Created by Huang
 * className: Service01Controller
 * Description:
 */
@RestController
@RequestMapping
public class Service02Controller {
    @DubboReference
    private Demo02Service demo02Service;

    @GetMapping("/service02/serviceCall")
    public JSONObject serviceCall(){
        return demo02Service.serviceCall();
    }
}
