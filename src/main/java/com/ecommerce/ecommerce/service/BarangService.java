package com.ecommerce.ecommerce.service;
import com.ecommerce.ecommerce.model.Barang;
import java.util.List;

public interface BarangService {
    Barang saveBarang(Barang employee);
    List<Barang> getAllBarang();
    Barang getBarangById(long id);
    Barang updateBarang(Barang employee,long id);
    void deleteBarang(long id);
}