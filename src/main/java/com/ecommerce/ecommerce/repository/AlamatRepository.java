package com.ecommerce.ecommerce.repository;
import com.ecommerce.ecommerce.model.Alamat;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlamatRepository extends JpaRepository<Alamat, Long>, JpaSpecificationExecutor<Alamat> {
}