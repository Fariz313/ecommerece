package com.ecommerce.ecommerce.service;
import com.ecommerce.ecommerce.model.Alamat;
import java.util.List;

public interface AlamatService {
    Alamat saveAlamat(Alamat alamat);
    List<Alamat> getAllAlamat(String nama, String icon,String id);
    Alamat getAlamatById(long id);
    Alamat updateAlamat(Alamat alamat,long id);
    void deleteAlamat(long id);
}