package org.example.service;

import org.example.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> save(User user);
    Optional<User> findByEmailAndPassword(String email, String password);
}
