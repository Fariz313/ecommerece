package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.model.Keranjang;
import com.ecommerce.ecommerce.repository.KeranjangRepository;
import com.ecommerce.ecommerce.service.KeranjangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class KeranjangServiceImpl implements KeranjangService {

    @Autowired
    private KeranjangRepository keranjangRepository;

    // save keranjang in database
    @Override
    public Keranjang saveKeranjang(Keranjang keranjang) {
        return keranjangRepository.save(keranjang);
    }

    // save keranjang in database
    @Override
    public Keranjang saveAndUpdateKeranjang(Keranjang keranjang) {
        Optional<Keranjang> existingKeranjang = keranjangRepository
                .findByIdUserAndIdBarang(keranjang.getIdUser(), keranjang.getIdBarang());
        
        if (existingKeranjang.isPresent()) {
            Keranjang keranjangToUpdate = existingKeranjang.get();
            keranjangToUpdate.setJumlah(keranjangToUpdate.getJumlah() + 1);
            return keranjangRepository.save(keranjangToUpdate);
        } else {
            return keranjangRepository.save(keranjang);
        }
    }

    // get all keranjang form database
    @Override
    public List<Keranjang> getAllKeranjang() {
        return keranjangRepository.findAll();
    }
    @Override
    public List<Keranjang> getUserKeranjang(long idUser) {
        return keranjangRepository.findByIdUser(idUser);
    }

    // get keranjang using id
    @Override
    public Keranjang getKeranjangById(long id) {
        Optional<Keranjang> keranjang = keranjangRepository.findById(id);
        if (keranjang.isPresent()) {
            return keranjang.get();
        } else {
            throw new RuntimeException();
        }
    }

    // update keranjang
    @Override
    public Keranjang updateKeranjang(Keranjang keranjang, long id) {
        Keranjang existingKeranjang = keranjangRepository.findById(id).orElseThrow(
                () -> new RuntimeException());
        existingKeranjang.setBarang(keranjang.getBarang());
        existingKeranjang.setIdUser(keranjang.getIdUser());
        existingKeranjang.setJumlah(keranjang.getJumlah());
        // save
        keranjangRepository.save(existingKeranjang);
        return existingKeranjang;
    }

    // delete keranjang
    @Override
    public void deleteKeranjang(long id) {
        // check
        keranjangRepository.findById(id).orElseThrow(() -> new RuntimeException());
        // delete
        keranjangRepository.deleteById(id);
    }

}