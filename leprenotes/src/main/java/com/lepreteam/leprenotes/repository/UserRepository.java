package com.lepreteam.leprenotes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lepreteam.leprenotes.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findAll();
}
