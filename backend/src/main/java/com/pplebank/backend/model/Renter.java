package com.pplebank.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="renter")
@Data
public class Renter implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @OneToMany(mappedBy="renter",fetch= FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Property> propertyList = new ArrayList<Property>();

    @NotEmpty(message = "Please enter name")
    @Column(name = "name", length = 64)
    private String name;

    @NotEmpty(message = "Please enter surname")
    @Column(name = "surname", length = 64)
    private String surname;
}
