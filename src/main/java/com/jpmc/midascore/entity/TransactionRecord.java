package com.jpmc.midascore.entity;

import jakarta.persistence.*;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private float amount;

    @JoinColumn(name = "transactionsRecord_id", nullable = false)
    @ManyToOne
    private long receiver_id;

    @JoinColumn(name = "transactionsRecord_id", nullable = false)
    @ManyToOne
    private long sender_id;


}
