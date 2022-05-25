package com.huang.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * @Time 2021-12-29 15:21
 * Created by Huang
 * className: CustomizeCorrelationData
 * Description:
 */
@EqualsAndHashCode(callSuper = true)
public class CustomizeCorrelationData extends CorrelationData {
    //消息体
    private volatile Object message;
    //交换机
    private String exchange;
    //路由键
    private String routingKey;
    //重试次数
    private int retryCount = 0;

    public CustomizeCorrelationData(String id, Object message, String exchange, String routingKey, int retryCount) {
        super(id);
        this.message = message;
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.retryCount = retryCount;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public String toString() {
        return "CustomizeCorrelationData{" +
                "message=" + message +
                ", exchange='" + exchange + '\'' +
                ", routingKey='" + routingKey + '\'' +
                ", retryCount=" + retryCount +
                "} " + super.toString();
    }
}
