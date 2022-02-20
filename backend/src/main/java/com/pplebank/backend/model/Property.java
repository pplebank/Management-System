package com.pplebank.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="property")
@Data
public class Property {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Renter.class)
    @JoinColumn(name="renter_id", referencedColumnName = "id", nullable = false)
    private Renter renter;

    @OneToMany(mappedBy="property",fetch= FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Reservation> reservationList = new ArrayList<Reservation>();

    private float price_per_day;
    private float area;
    private String description;
}
