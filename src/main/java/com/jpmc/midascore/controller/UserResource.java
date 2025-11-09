package com.jpmc.midascore.controller;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserResource {

    @Autowired
    UserRepository repo;

    @GetMapping("/balance")
    public Balance balance(@RequestParam Long userId){

        Optional<UserRecord> findUser = repo.findById(userId);

        if(findUser.isPresent()){
            UserRecord user = findUser.get();
            return new Balance(user.getBalance());
        }
        return new Balance(0);
    }
}
