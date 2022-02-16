package com.pplebank.backend.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private int id;


    @OneToMany(mappedBy="client",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Reservation> reservationList = new ArrayList<Reservation>();



}
