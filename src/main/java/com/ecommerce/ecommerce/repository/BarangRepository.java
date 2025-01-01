package com.ecommerce.ecommerce.repository;
import com.ecommerce.ecommerce.model.Barang;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BarangRepository extends JpaRepository<Barang, Long>, JpaSpecificationExecutor<Barang> {
}