package com.ecommerce.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_order", nullable = false)
    private long idOrder;

    @Column(name = "id_barang", nullable = false)
    private long idBarang;

    @Column(name = "barang_nama", nullable = false)
    private String barangNama;

    @Column(name = "barang_harga", nullable = false)
    private double barangHarga;

    @Column(name = "barang_gambar", nullable = false)
    private String barangGambar;

    @Column(name = "status_pengiriman", nullable = true)
    private String statusPEngiriman;

    @Column(name = "jumlah", nullable = false)
    private int jumlah;

    // This removes the relationship with Barang and stores static information directly in the cart.
}
