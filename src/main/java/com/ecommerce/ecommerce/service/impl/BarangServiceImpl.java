package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.model.Barang;
import com.ecommerce.ecommerce.repository.BarangRepository;
import com.ecommerce.ecommerce.service.BarangService;
import com.ecommerce.ecommerce.specification.BarangSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    public List<Barang> getAllBarang(String nama, String kategori, String harga, String toko, String gambar) {
        Specification<Barang> spec = Specification.where(BarangSpecification.hasNama(nama))
            .and(BarangSpecification.hasKategori(kategori))
            .and(BarangSpecification.hasHarga(harga))
            .and(BarangSpecification.hasToko(toko))
            .and(BarangSpecification.hasGambar(gambar));

        return barangRepository.findAll(spec);
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