package com.pplebank.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="renter")
@Data
public class Renter {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy="renter",fetch= FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Property> propertyList = new ArrayList<Property>();

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;
}
