package com.example.service;

import java.util.Collection;
import java.util.Optional;

import com.example.dto.UserCreateForm;
import com.example.model.User;

public interface UserService {

    Optional<User> getUserById(long id);

    User getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}