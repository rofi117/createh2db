package com.example.springdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springdb.model.Beneficiary;
import com.example.springdb.repo.BeneficiaryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryService {

    @Autowired
    BeneficiaryRepo repo;
    public Beneficiary save(Beneficiary beneficiary) {
        return repo.save(beneficiary);
    }

    public List<Beneficiary> findAll()
    {
        return repo.findAll();
    }

    public String deleteAll()
    {
        repo.deleteAll();
        return "All Beneficiary Records Deleted Successfully";
    }

    public boolean deleteBeneficiaryByID(Integer id)
    {
        repo.deleteById(id);
        return true;
    }

    public boolean checkBeneficiaryExistById(Integer id)
    {
        repo.existsById(id);
        return true;
    }

    public Beneficiary updateBeneficiaryById(Beneficiary beneficiary, Integer id) {
        Beneficiary updateBeneficiary = new Beneficiary();
        updateBeneficiary.setId(id);
        updateBeneficiary.setBeneficiaryName(beneficiary.getBeneficiaryName());
        updateBeneficiary.setBeneficiaryAccNo(beneficiary.getBeneficiaryAccNo());
        updateBeneficiary.setBeneficiaryBank(beneficiary.getBeneficiaryBank());
        updateBeneficiary.setBeneficiaryIFSC(beneficiary.getBeneficiaryIFSC());
        return repo.save(updateBeneficiary);
    }

    public Optional<Beneficiary> findBeneficiaryById(Integer id) {
        return repo.findById(id);
    }
}
