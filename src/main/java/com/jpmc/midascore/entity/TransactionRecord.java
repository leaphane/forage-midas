package com.jpmc.midascore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

@Getter
@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue
    private long id;

    @Setter
    @Column(nullable = false)
    private float amount;

    @Setter
    @JoinColumn(name = "receiver_id", nullable = false)
    @ManyToOne
    private UserRecord receiver;

    @JoinColumn(name = "sender_id", nullable = false)
    @ManyToOne
    @Setter
    private UserRecord sender;

    public TransactionRecord(float amount, UserRecord receiver, UserRecord sender){

        this.amount = amount;
        this.receiver = receiver;
        this.sender = sender;

    }


}
