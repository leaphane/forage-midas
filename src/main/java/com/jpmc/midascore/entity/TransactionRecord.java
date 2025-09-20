package com.jpmc.midascore.entity;

import jakarta.persistence.*;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue
    private  int id;

    @Column(nullable = false)
    private int amount;

    @JoinColumn(name = "transactionsRecord_id", nullable = false)
    @ManyToOne
    private int receiver_id;

    @JoinColumn(name = "transactionsRecord_id", nullable = false)
    @ManyToOne
    private int sender_id;


}
