package com.pplebank.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name="reservation")
@Data
public class Reservation implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Need client's id to make reservation")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Client.class)
    @JsonManagedReference
    @JoinColumn(name="client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    @NotEmpty(message = "Need property's id to make reservation")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Property.class)
    @JsonManagedReference
    @JoinColumn(name="property_id", referencedColumnName = "id", nullable = false)
    private Property property;

    @Transient
    @Column(name = "total_cost")
    private float total_cost;

    @Transient
    @Column(name = "number_of_days")
    private long number_of_days;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    @PostLoad
    private void onLoad() {
        long diff = end_date.getTime() - start_date.getTime();
        this.number_of_days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        this.total_cost = this.number_of_days * this.property.getPrice_per_day();
    }

}
