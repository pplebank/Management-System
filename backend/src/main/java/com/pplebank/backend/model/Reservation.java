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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Property.class)
    @JoinColumn(name="property_id", referencedColumnName = "id", nullable = false)
    private Property property;

    private int total_price;
    private int days_number;
    private Date start_date;
    private Date end_date;

}
