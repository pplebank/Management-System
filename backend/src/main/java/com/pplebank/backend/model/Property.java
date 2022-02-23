package com.pplebank.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="property")
@Data
public class Property implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Property have to has it's owner")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Renter.class)
    @JsonManagedReference
    @JoinColumn(name="renter_id", referencedColumnName = "id", nullable = false)
    private Renter renter;

    @JsonBackReference
    @OneToMany(mappedBy="property",fetch= FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Reservation> reservationList = new ArrayList<Reservation>();

    @NotEmpty(message = "Name of property cannot be empty")
    @Column(name = "name", length = 64)
    private String name;

    @NotEmpty(message = "Address cannot be empty")
    @Column(name = "address", length = 128)
    private String address;

    @Column(name = "price_per_day")
    @Positive(message = "price must be greater than 0")
    private float price_per_day;

    @Column(name = "area")
    @Positive(message = "area must be greater than 0")
    private float area;

    @NotEmpty(message = "description cannot be empty")
    @Column(name = "description", length = 4096)
    private String description;
}
