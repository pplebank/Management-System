package com.pplebank.backend.model;

import javax.persistence.*;

public class Property {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Renter.class)
    @JoinColumn(name="renter_id", referencedColumnName = "id", nullable = false)
    private Renter renter;

    private int price_per_day;
    private int area;
    private String description;
}
