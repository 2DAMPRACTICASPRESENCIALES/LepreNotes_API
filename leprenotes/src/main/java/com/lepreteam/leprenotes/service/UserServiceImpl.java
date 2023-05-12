package com.lepreteam.leprenotes.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exceptions.NotFoundException;
import com.lepreteam.leprenotes.domain.User;
import com.lepreteam.leprenotes.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() { return userRepository.findAll(); }

    @Override
    public User getUserById(long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(new User()));
    }

    @Override
    public User addUser(User user) {
        User oldUser = new User();

        modelMapper.map(user, oldUser);

        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUser(long id) throws NotFoundException {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(new User()));
        userRepository.delete(user);
    }

    @Override
    public User modifyUser(long id, User user) throws NotFoundException {
        User userModified = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(new User()));
        
        modelMapper.map(user, userModified);

        return userRepository.save(userModified);
    }
}
