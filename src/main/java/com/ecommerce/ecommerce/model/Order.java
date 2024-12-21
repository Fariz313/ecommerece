package com.ecommerce.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "keranjang")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_user", nullable = false)
    private long idUser;

    @Column(name = "id_barang", nullable = false)
    private long idBarang;

    @Column(name = "barang_nama", nullable = false)
    private String barangNama;

    @Column(name = "barang_harga", nullable = false)
    private double barangHarga;

    @Column(name = "barang_gambar", nullable = false)
    private String barangGambar;

    @Column(name = "jumlah", nullable = false)
    private int jumlah;

    // This removes the relationship with Barang and stores static information directly in the cart.
}
