package com.huang.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Time 2022-05-23 17:06
 * Created by Huang
 * className: ServiceClient
 * Description:
 */
@FeignClient(value = "huang-service-01-20001")
public interface ServiceClient {

    @GetMapping("/service01/serviceCall")
    JSONObject testCall();
}
