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
public class Keranjang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_user",nullable = false)
    private long idUser;
    @Column(name = "id_barang", nullable = false)
    private long idBarang;
    @ManyToOne
    @JoinColumn(name = "id_barang", referencedColumnName = "id", insertable = false, updatable = false)
    private Barang barang;
    @Column(name = "jumlah",nullable = false)
    private int jumlah;
    
}