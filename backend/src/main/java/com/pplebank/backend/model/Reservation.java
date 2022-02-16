package com.pplebank.backend.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity


public class Reservation {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Client.class)
    @JoinColumn(name="client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    private String name;
    private String address;


    @Size(min=10, message="Enter at least 10 Characters...")
    private String desc;
}
