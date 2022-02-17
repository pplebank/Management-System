package com.pplebank.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Renter {

    @OneToMany(mappedBy="client",fetch= FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Property> propertyList = new ArrayList<Property>();

    private String name;
    private String surname;
}
