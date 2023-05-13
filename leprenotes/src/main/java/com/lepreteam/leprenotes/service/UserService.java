package com.lepreteam.leprenotes.service;

import java.util.List;

import com.exceptions.NotFoundException;
import com.lepreteam.leprenotes.domain.User;

public interface UserService {
    List<User> findAll();

    User getUserById(long id) throws NotFoundException;

    User addUser(User user);

    void deleteUser(long id) throws NotFoundException;

    User modifyUser(long id, User user) throws NotFoundException;
}
