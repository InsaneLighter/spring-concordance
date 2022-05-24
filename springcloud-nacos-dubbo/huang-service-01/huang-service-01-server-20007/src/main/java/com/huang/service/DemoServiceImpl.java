package com.huang.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Time 2022-05-24 8:48
 * Created by Huang
 * className: DemoServiceImpl
 * Description:
 */
//在dubbo 2.7.8中注解
//@Service被@DubboService 取代
//@Reference被@DubboReference取代
@DubboService
public class DemoServiceImpl implements Demo01Service {
    @Override
    public JSONObject serviceCall() {
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("note","huang-service-01-server-20007 serviceCall success");
        return result;
    }
}
