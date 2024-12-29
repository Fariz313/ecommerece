package com.ecommerce.ecommerce.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "barang")
public class Barang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nama",nullable = false)
    private String nama;
    @Column(name = "harga",nullable = false)
    private String harga;
    @Column(name = "toko",nullable = false)
    private String toko;
    @Column(name = "gambar",nullable = false)
    private String gambar;
    @Column(name = "kategori",nullable = false)
    private String kategori;
}