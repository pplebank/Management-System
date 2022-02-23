package com.pplebank.backend.service;

import com.pplebank.backend.model.Client;
import com.pplebank.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    public List<Client> listAllClients() {
        return clientRepository.findAll();
    }
    public Optional getClientById(Long client_id) {
        return clientRepository.findById(client_id);
    }
    public boolean modifyClientDataById(Long client_id, Client requested_client) {
        if (clientRepository.findById(client_id).isPresent()) {
            clientRepository.findById(client_id).map(client -> {
                client.setName(requested_client.getName());
                client.setSurname(requested_client.getSurname());

                return clientRepository.save(client);
            });
            return true;
        }
        return false;
    }
    public void createNewClient(Client requested_client) {
         clientRepository.save(requested_client);
    }
    public void deleteClient(Long client_id) {
        clientRepository.deleteById(client_id);
    }
}
