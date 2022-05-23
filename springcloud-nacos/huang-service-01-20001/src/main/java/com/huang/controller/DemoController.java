package com.huang.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time 2022-05-20 13:20
 * Created by Huang
 * className: DemoController
 * Description:
 */
@RestController
@RequestMapping("/service01")
public class DemoController {
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @GetMapping("/config")
    public String configLoad(){
        return applicationContext.getEnvironment().getProperty("config.column");
    }

    @GetMapping("/ext/config")
    public String extConfigLoad(){
        return applicationContext.getEnvironment().getProperty("ext.config01") + "\r" + applicationContext.getEnvironment().getProperty("ext.config02");
    }


    @GetMapping
    public JSONObject testCall(){
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("note","success");
        return result;
    }
}
