package com.spring.kafka.service;

import com.spring.kafka.model.User;
import com.spring.kafka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceIml implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public User createNewUser(User created) {
        return repository.save(created);
    }


    @Override
    public User updateUser(User updated) {

        User checkUser = findById(updated.getId());

        if(checkUser != null){
            checkUser.setName(updated.getName());
            checkUser.setAge(updated.getAge());
            return repository.saveAndFlush(checkUser);
        }

        return null;

    }


    @Override
    public List<User> findAllUser() {
        return repository.findAll();
    }


    @Override
    public User deleteUserById(int userId) {

        User deleted = findById(userId);

        if(deleted != null) {
            repository.delete(deleted);
            return deleted;
        }

        return null;

    }


    @Override
    public User findUserById(int userId) {
        User user = repository.findById(userId).orElse(null);
        return user;
    }
}
