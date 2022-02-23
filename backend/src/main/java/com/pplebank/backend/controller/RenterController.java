package com.pplebank.backend.controller;

import com.pplebank.backend.model.Renter;
import com.pplebank.backend.model.Reservation;
import com.pplebank.backend.service.RenterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RenterController {

    private RenterService renterService;

    @Autowired
    public RenterController(RenterService renterService) {
        this.renterService = renterService;
    }

    @ApiOperation(value = "list all renters", response = List.class)
    @RequestMapping(value = "/renters", method = RequestMethod.GET)
    public ResponseEntity<?> listAllRenters() {
        try {
            List<Renter> response = renterService.listAllRenters();
            return ResponseEntity.ok(response);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }


    @ApiOperation(value = "Get renter by id", response = Reservation.class)
    @RequestMapping(value = "renter/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRenterById(@Valid @PathVariable Long id) {
        try {
            Renter response = renterService.getRenterById(id);
            return ResponseEntity.ok(response);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }

    @ApiOperation(value = "modify renter data", response = String.class)
    @RequestMapping(value = "renter/modify/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyRenterDataById(@Valid @PathVariable Long id, Renter renter) {
        try {
            boolean error = renterService.modifyRenterDataById(id, renter);
            if (error) {
                return ResponseEntity.ok("Record successful modified");
            }
            return ResponseEntity.badRequest().body("Could not find client with this id");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }

    @ApiOperation(value = "create new renter", response = String.class)
    @RequestMapping(value = "renter/new", method = RequestMethod.POST)
    public ResponseEntity<?> createNewClient(@Valid Renter renter) {
        try {
            renterService.createNewRenter(renter);
            return ResponseEntity.ok("Record successful created");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }

    @ApiOperation(value = "delete renter by id", response = List.class)
    @RequestMapping(value = "renter/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClient(@Valid @PathVariable Long id) {
        try {
            renterService.deleteRenter(id);
            return ResponseEntity.ok("Record successful deleted");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }
}
