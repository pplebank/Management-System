package com.pplebank.backend.controller;

import com.pplebank.backend.model.Client;
import com.pplebank.backend.model.Property;
import com.pplebank.backend.model.Reservation;
import com.pplebank.backend.service.PropertyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PropertyController {
    private PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @ApiOperation(value = "list all properties", response = Client.class)
    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public ResponseEntity<?> listAllCProperties() {
        try {
            List<Property> response = propertyService.listAllCProperties();
            return ResponseEntity.ok(response);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }

    @ApiOperation(value = "Get property by id", response = Reservation.class)
    @RequestMapping(value = "/property/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPropertyById(@Valid @PathVariable Long id) {
        try {
            Optional response = propertyService.getPropertyById(id);
            return ResponseEntity.ok(response);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }

    @ApiOperation(value = "Get property by owner id", response = Reservation.class)
    @RequestMapping(value = "/property/owner/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPropertyByOwnerId(@Valid @PathVariable Long id) {
        try {
            Optional<Property> response = propertyService.getPropertyByOwnerId(id);
            return ResponseEntity.ok(response);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }

    @ApiOperation(value = "modify property data", response = String.class)
    @RequestMapping(value = "/property/modify/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyPropertyById(@Valid @PathVariable Long id, Property property) {
        try {
            boolean error = propertyService.modifyPropertyDataById(id, property);
            if (error) {
                return ResponseEntity.ok("Record successful modified");
            }
            return ResponseEntity.badRequest().body("Could not find property with this id");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }

    @ApiOperation(value = "create new property", response = String.class)
    @RequestMapping(value = "/property/new", method = RequestMethod.POST)
    public ResponseEntity<?> createNewProperty(@Valid Property property) {
        try {
            propertyService.createNewProperty(property);
            return ResponseEntity.ok("Record successful created");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }

    @ApiOperation(value = "delete property by id", response = List.class)
    @RequestMapping(value = "/property/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClient(@Valid @PathVariable Long id) {
        try {
            propertyService.deleteProperty(id);
            return ResponseEntity.ok("Record successful deleted");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }
}
