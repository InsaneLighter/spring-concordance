package com.huang.controller;

import com.alibaba.fastjson.JSONObject;
import com.huang.service.Demo01Service;
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
public class Service01Controller {
    @DubboReference
    private Demo01Service demo01Service;

    @GetMapping("/service01/serviceCall")
    public JSONObject serviceCall(){
        return demo01Service.serviceCall();
    }
}
