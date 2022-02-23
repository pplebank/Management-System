package com.pplebank.backend.service;

import com.pplebank.backend.model.Renter;
import com.pplebank.backend.repository.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenterService {

    @Autowired
    private RenterRepository renterRepository;

    public List<Renter> listAllRenters() {
        return renterRepository.findAll();
    }

    public Renter getRenterById(Long renter_id) {
        return renterRepository.getById(renter_id);
    }

    public boolean modifyRenterDataById(Long renter_id, Renter requested_renter) {
        if (renterRepository.findById(renter_id).isPresent()) {
            renterRepository.findById(renter_id).map(renter -> {
                renter.setName(requested_renter.getName());
                renter.setSurname(requested_renter.getSurname());

                return renterRepository.save(renter);
            });
            return true;
        }
        return false;
    }

    public void createNewRenter(Renter requested_renter) {
        renterRepository.save(requested_renter);
    }

    public void deleteRenter(Long renter_id) {
        renterRepository.deleteById(renter_id);
    }
}
