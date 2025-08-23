package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SimpleConsumer {


    @KafkaListener(topics = "${general.kafka-topic}", groupId = "group1")
    public void consume(String transaction){

        System.out.println(transaction);


    }

}
