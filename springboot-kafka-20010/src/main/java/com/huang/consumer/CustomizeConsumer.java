package com.huang.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @Time 2022-01-24 15:55
 * Created by Huang
 * className: KafkaConsumer
 * Description:
 */
@Component
@Slf4j
public class CustomizeConsumer {

    @KafkaListener(topics = {"push_message"})
    public void consumer(ConsumerRecord<?, ?> record){
        log.info("consumer: {}-{}-{}",record.topic(),record.partition(),record.value());
    }

}

