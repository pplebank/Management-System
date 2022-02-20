package com.pplebank.backend.model;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="client")
@Data
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy="client",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Reservation> reservationList = new ArrayList<Reservation>();

    @Length(min = 1, max = 64)
    private String name;

    @Length(min = 1, max = 64)
    private String surname;
}
