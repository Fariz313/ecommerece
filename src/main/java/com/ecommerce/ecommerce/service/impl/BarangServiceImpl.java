package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.model.Barang;
import com.ecommerce.ecommerce.repository.BarangRepository;
import com.ecommerce.ecommerce.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BarangServiceImpl implements BarangService {

    @Autowired
    private BarangRepository barangRepository;

    // save barang in database
    @Override
    public Barang saveBarang(Barang barang) {
        return barangRepository.save(barang);
    }

    // get all barang form database
    @Override
    public List<Barang> getAllBarang() {
        return barangRepository.findAll();
    }

    // get barang using id
    @Override
    public Barang getBarangById(long id) {
        Optional<Barang> barang = barangRepository.findById(id);
        if (barang.isPresent()) {
            return barang.get();
        } else {
            throw new RuntimeException();
        }
    }

    // update barang
    @Override
    public Barang updateBarang(Barang barang, long id) {
        Barang existingBarang = barangRepository.findById(id).orElseThrow(
                () -> new RuntimeException());
        existingBarang.setNama(barang.getNama());
        existingBarang.setGambar(barang.getGambar());
        existingBarang.setHarga(barang.getHarga());
        existingBarang.setToko(barang.getToko());
        // save
        barangRepository.save(existingBarang);
        return existingBarang;
    }

    // delete barang
    @Override
    public void deleteBarang(long id) {
        // check
        barangRepository.findById(id).orElseThrow(() -> new RuntimeException());
        // delete
        barangRepository.deleteById(id);
    }

}