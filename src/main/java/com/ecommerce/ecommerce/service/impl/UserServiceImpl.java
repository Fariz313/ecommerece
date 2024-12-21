package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.UserRepsitory;
import com.ecommerce.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserService {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepsitory userRepsitory;

    // save user in database
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepsitory.save(user);
    }

    // get all user form database
    @Override
    public List<User> getAllUser() {
        return userRepsitory.findAll();
    }

    // get user using id
    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepsitory.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException();
        }
    }

    // update user
    @Override
    public User updateUser(User user, long id) {
        User existingUser = userRepsitory.findById(id).orElseThrow(
                () -> new RuntimeException());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        // save
        userRepsitory.save(existingUser);
        return existingUser;
    }

    // delete user
    @Override
    public void deleteUser(long id) {
        // check
        userRepsitory.findById(id).orElseThrow(() -> new RuntimeException());
        // delete
        userRepsitory.deleteById(id);
    }

    @Override
    public User login(String email, String password) {
        User user = userRepsitory.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
}