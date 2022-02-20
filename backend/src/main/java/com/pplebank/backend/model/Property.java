package com.pplebank.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
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

    @NotEmpty(message = "Name of property cannot be empty")
    @Column(length = 64)
    private String name;

    @NotEmpty(message = "Address cannot be empty")
    @Column(length = 128)
    private String address;

    @Positive(message = "price must be greater than 0")
    private float price_per_day;

    @Positive(message = "area must be greater than 0")
    private float area;

    @NotEmpty(message = "description cannot be empty")
    @Column(length = 4096)
    private String description;
}
