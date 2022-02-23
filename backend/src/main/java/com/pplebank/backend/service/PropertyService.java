package com.pplebank.backend.service;

import com.pplebank.backend.model.Property;
import com.pplebank.backend.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    public List<Property> listAllCProperties() {
        return propertyRepository.findAll();
    }
    public Optional<Property> getPropertyById(Long property_id) {
        return propertyRepository.findById(property_id);
    }
    public boolean modifyPropertyDataById(Long property_id, Property requested_property) {
        if (propertyRepository.findById(property_id).isPresent()) {
            propertyRepository.findById(property_id).map(property -> {
                property.setName(requested_property.getName());
                property.setRenter(requested_property.getRenter());
                property.setAddress(requested_property.getAddress());
                property.setPrice_per_day(requested_property.getPrice_per_day());
                property.setArea(requested_property.getArea());
                property.setDescription(requested_property.getDescription());
                return propertyRepository.save(property);
            });
            return true;
        }
        return false;
    }
    public Optional<Property> getPropertyByOwnerId(Long renter_id) {
        return propertyRepository.getPropertyByRenterId(renter_id);
    }

    public void createNewProperty(Property requested_property) {
        propertyRepository.save(requested_property);
    }
    public void deleteProperty(Long property_id) {
        propertyRepository.deleteById(property_id);
    }
}
