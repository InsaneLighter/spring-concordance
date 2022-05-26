package com.huang.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Time 2021-09-22 17:28
 * Created by Huang
 * className: DefinedKafkaProducer
 * Description:
 */
@Slf4j
@Component
public class DefinedKafkaProducer {
    private static KafkaProducer<String, String> producer;

    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "server1:9092");
        props.put("acks", "all");
        props.put("retries", "3");
        props.put("batch.size", "16384");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("max.block.ms", "120000");
        props.put("request.timeout.ms", "120000");
        producer = new KafkaProducer<String, String>(props);
    }

    public Boolean sendMessage(String message){
        AtomicReference<Boolean> flag = new AtomicReference<>(true);
        log.info("kafka-topic: {} 发送消息内容: {}","topic",message);
        producer.send(new ProducerRecord<String, String>("topic", message),(metadata, exception) -> {
            if(exception == null){
                log.info("kafka消息发送成功-topic:{} offset:{} partition:{}",metadata.topic(),metadata.offset(),metadata.partition());
            }else {
                log.info("kafka消息发送失败-exception: {}",exception.getMessage());
                flag.getAndSet(false);
                exception.printStackTrace();
            }
        });
        return flag.get();
    }
}
