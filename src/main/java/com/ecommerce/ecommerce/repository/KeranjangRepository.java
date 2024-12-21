package com.ecommerce.ecommerce.repository;
import com.ecommerce.ecommerce.model.Keranjang;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KeranjangRepository extends JpaRepository<Keranjang,Long> {
    @Query("SELECT k FROM Keranjang k WHERE k.idUser = :idUser AND k.idBarang = :idBarang")
    Optional<Keranjang> findByIdUserAndIdBarang(@Param("idUser") long idUser, @Param("idBarang") long idBarang);
    @Query("SELECT k FROM Keranjang k WHERE k.idUser = :idUser")
    List<Keranjang> findByIdUser(long idUser);
    @Query("SELECT k FROM Keranjang k JOIN FETCH k.barang WHERE k.idUser = :idUser")
    List<Keranjang> findKeranjangWithBarangByUserId(@Param("idUser") long idUser);
}