package com.ltdd14.FarmMarket.model.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao{

    @Autowired
    private  UserRepository repository;


    public User save(User user){
        return repository.save(user);
    }


    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(users::add);
        return users;
    }

    public String checkUsername(String username) {
        return repository.checkUsername(username);
    }

    public String checkPassword(String username) {
        return repository.checkPassword(username);
    }

    public User getUser(String username) {
        return repository.getUserByUsername(username);
    }




    public void deleteUserById(int Id){
        repository.deleteById(Id);
    }
}
