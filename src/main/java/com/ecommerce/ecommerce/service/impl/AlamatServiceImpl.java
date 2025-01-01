package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.model.Alamat;
import com.ecommerce.ecommerce.repository.AlamatRepository;
import com.ecommerce.ecommerce.service.AlamatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AlamatServiceImpl implements AlamatService {

    @Autowired
    private AlamatRepository alamatRepository;

    // save Alamat in database
    @Override
    public Alamat saveAlamat(Alamat Alamat) {
        return alamatRepository.save(Alamat);
    }

    // get all Alamat form database
    @Override
    public List<Alamat> getAllAlamat(String nama, String icon) {
        return alamatRepository.findAll();
    }

    // get Alamat using id
    @Override
    public Alamat getAlamatById(long id) {
        Optional<Alamat> Alamat = alamatRepository.findById(id);
        if (Alamat.isPresent()) {
            return Alamat.get();
        } else {
            throw new RuntimeException();
        }
    }

    // update Alamat
    @Override
    public Alamat updateAlamat(Alamat Alamat, long id) {
        Alamat existingAlamat = alamatRepository.findById(id).orElseThrow(
                () -> new RuntimeException());
        existingAlamat.setPenerima(Alamat.getPenerima());
        existingAlamat.setKodePos(Alamat.getKodePos());
        existingAlamat.setKontak(Alamat.getKontak());
        existingAlamat.setAlamat(Alamat.getAlamat());
        // save
        alamatRepository.save(existingAlamat);
        return existingAlamat;
    }

    // delete Alamat
    @Override
    public void deleteAlamat(long id) {
        // check
        alamatRepository.findById(id).orElseThrow(() -> new RuntimeException());
        // delete
        alamatRepository.deleteById(id);
    }

}