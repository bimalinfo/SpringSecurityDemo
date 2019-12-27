package com.learning.auth.service;

import com.learning.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
