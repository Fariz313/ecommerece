package com.ecommerce.ecommerce.repository;
import com.ecommerce.ecommerce.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepsitory extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}