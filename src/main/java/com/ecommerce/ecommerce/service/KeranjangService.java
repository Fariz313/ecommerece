package com.ecommerce.ecommerce.service;
import com.ecommerce.ecommerce.model.Keranjang;
import java.util.List;
import java.util.Optional;

public interface KeranjangService {
    Keranjang saveKeranjang(Keranjang keranjang);
    Keranjang saveAndUpdateKeranjang(Keranjang keranjang);
    List<Keranjang> getAllKeranjang();
    List<Keranjang> getUserKeranjang(long idUser);
    Keranjang getKeranjangById(long id);
    Keranjang updateKeranjang(Keranjang keranjang,long id);
    void deleteKeranjang(long id);
}