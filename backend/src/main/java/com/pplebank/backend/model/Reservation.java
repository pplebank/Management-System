package com.pplebank.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reservation")
@Data
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Client.class)
    @JoinColumn(name="client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Property.class)
    @JoinColumn(name="property_id", referencedColumnName = "id", nullable = false)
    private Property property;

    @Column(name = "total_price")
    private float total_price;

    @Column(name = "number_of_days")
    private int number_of_days;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

}
