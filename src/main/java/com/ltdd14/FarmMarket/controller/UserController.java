package com.ltdd14.FarmMarket.controller;
import com.ltdd14.FarmMarket.model.user.User;
import com.ltdd14.FarmMarket.model.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;


    @GetMapping("/user/get-all")
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @PostMapping("/user/save")
    public User save(@RequestBody User user) {
        return userDao.save(user);
    }

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody User user) {

        User userAuth = userDao.getUser(user.getUsername());

        if(userAuth == null) {
            return new ResponseEntity<>("Username or Password incorrect", HttpStatus.NOT_FOUND);
        }

        if(!userAuth.getPassword().equals(user.getPassword())){
            return new ResponseEntity<>("Username or Password incorrect", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity(userAuth, HttpStatus.OK);
    }
}
