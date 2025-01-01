package com.ecommerce.ecommerce.specification;

import org.springframework.data.jpa.domain.Specification;
import com.ecommerce.ecommerce.model.Barang;

public class BarangSpecification {

    public static Specification<Barang> hasKategori(String kategori) {
        return (root, query, criteriaBuilder) -> 
            kategori == null ? null : criteriaBuilder.like(root.get("kategori"), "%" + kategori + "%");
    }

    public static Specification<Barang> hasNama(String nama) {
        return (root, query, criteriaBuilder) -> 
            nama == null ? null : criteriaBuilder.like(root.get("nama"), "%" + nama + "%");
    }

    public static Specification<Barang> hasHarga(String harga) {
        return (root, query, criteriaBuilder) -> 
            harga == null ? null : criteriaBuilder.like(root.get("harga"), "%" + harga + "%");
    }

    public static Specification<Barang> hasToko(String toko) {
        return (root, query, criteriaBuilder) -> 
            toko == null ? null : criteriaBuilder.like(root.get("toko"), "%" + toko + "%");
    }

    public static Specification<Barang> hasGambar(String gambar) {
        return (root, query, criteriaBuilder) -> 
            gambar == null ? null : criteriaBuilder.like(root.get("gambar"), "%" + gambar + "%");
    }
}
