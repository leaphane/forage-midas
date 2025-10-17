package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;;
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
        String new_trans =  transaction.replaceAll("[^0-9.,]", "");
        String[] stringTransaction = new_trans.split(",");

        Transaction transaction1 = new Transaction(Long.parseLong(stringTransaction[0]), Long.parseLong(stringTransaction[1]), Float.parseFloat(stringTransaction[2]));
        UserRecord user_sender = userRepo.findById(transaction1.getSenderId());
        UserRecord user_receiver = userRepo.findById(transaction1.getRecipientId());
        System.out.println(user_sender.getBalance());
        System.out.println(user_receiver.getBalance());
        System.out.println("after");
        if(user_sender.getBalance() >= transaction1.getAmount()){

            user_sender.setBalance(user_sender.getBalance() - transaction1.getAmount());
            user_receiver.setBalance(user_receiver.getBalance() + transaction1.getAmount());
            userRepo.save(user_receiver);
            userRepo.save(user_sender);

        }
        System.out.println(user_sender.getBalance());
        System.out.println(user_receiver.getBalance());

    }

}
