package com.pplebank.backend.repository;

import com.pplebank.backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {

}
