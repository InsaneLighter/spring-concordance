package com.huang.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Time 2022-05-26 9:24
 * Created by Huang
 * className: CommonServiceImpl
 * Description:
 */
@Service
public class CommonServiceImpl implements CommonService{
    @Override
    public JSONObject commonRequest() {
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("note","huang-service-02-server-20008 commonRequest success");
        return result;
    }
}
