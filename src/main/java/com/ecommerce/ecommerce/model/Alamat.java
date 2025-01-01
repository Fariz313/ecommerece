package com.ecommerce.ecommerce.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "alamats")
public class Alamat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "kode_pos",nullable = false)
    private String kodePos;
    @Column(name = "alamat",nullable = false)
    private String alamat;
    @Column(name = "penerima",nullable = false)
    private String penerima;
    @Column(name = "kontak",nullable = false)
    private String kontak;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}