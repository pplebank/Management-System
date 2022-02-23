package com.pplebank.backend.controller;

import com.pplebank.backend.model.Client;
import com.pplebank.backend.model.Reservation;
import com.pplebank.backend.service.ClientService;
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
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "list all clients", response = Client.class)
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<?> listAllClients() {
        try {
            List<Client> response = clientService.listAllClients();
            return ResponseEntity.ok(response);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }


    @ApiOperation(value = "Get client by id", response = Reservation.class)
    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getClientById(@Valid @PathVariable Long id) {
        try {
            Optional response = clientService.getClientById(id);
            return ResponseEntity.ok(response);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }

    @ApiOperation(value = "modify client data", response = String.class)
    @RequestMapping(value = "/client/modify/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyClientById(@Valid @PathVariable Long id, Client client) {
        try {
            boolean error = clientService.modifyClientDataById(id, client);
            if (error) {
                return ResponseEntity.ok("Record successful modified");
            }
            return ResponseEntity.badRequest().body("Could not find client with this id");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }

    @ApiOperation(value = "create new client", response = String.class)
    @RequestMapping(value = "/client/new", method = RequestMethod.POST)
    public ResponseEntity<?> createNewClient(@Valid Client client) {
        try {
            clientService.createNewClient(client);
            return ResponseEntity.ok("Record successful created");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }

    @ApiOperation(value = "delete client by id", response = List.class)
    @RequestMapping(value = "/client/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClient(@Valid @PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.ok("Record successful deleted");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("error message=" + error.getMessage() + ";cause message=" + error.getCause());
        }
    }
}