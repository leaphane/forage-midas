package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repos.UserRepo;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleConsumer {

    @Autowired
    private UserRepository userRepo;


    @KafkaListener(topics = "${general.kafka-topic}", groupId = "group1")
    public void consume(String transaction){

        System.out.println(transaction);
        String[] stringTransaction =  transaction.split("=");
        Transaction transaction1 = new Transaction(Long.valueOf(stringTransaction[2]), Long.valueOf(stringTransaction[4]), Float.valueOf(stringTransaction[6]));
        UserRecord user_sender = userRepo.findById(transaction1.getSenderId());
        UserRecord user_receiver = userRepo.findById(transaction1.getRecipientId());

        if(user_sender.getBalance() >= transaction1.getAmount()){

            user_sender.setBalance(user_sender.getBalance() - transaction1.getAmount());
            user_receiver.setBalance(user_receiver.getBalance() + transaction1.getAmount());
            userRepo.save(user_receiver);
            userRepo.save(user_sender);

        }

    }

}
