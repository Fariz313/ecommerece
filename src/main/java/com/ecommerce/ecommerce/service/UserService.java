package com.ecommerce.ecommerce.service;
import com.ecommerce.ecommerce.model.User;
import java.util.List;

public interface UserService {
    User saveUser(User employee);
    List<User> getAllUser();
    User getUserById(long id);
    User updateUser(User employee,long id);
    void deleteUser(long id);
    
    User login(String email, String password);
}