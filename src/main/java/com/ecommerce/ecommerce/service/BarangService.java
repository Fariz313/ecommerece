package com.ecommerce.ecommerce.service;
import com.ecommerce.ecommerce.model.Barang;
import java.util.List;

public interface BarangService {
    Barang saveBarang(Barang barang);
    List<Barang> getAllBarang(String nama, String kategori, String harga, String toko, String gambar,String id);
    Barang getBarangById(long id);
    Barang updateBarang(Barang barang,long id);
    void deleteBarang(long id);
}