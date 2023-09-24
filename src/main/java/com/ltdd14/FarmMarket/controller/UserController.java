package com.ltdd14.FarmMarket.controller;
import com.google.gson.Gson;
import com.ltdd14.FarmMarket.config.JWTGenerator;
import com.ltdd14.FarmMarket.model.user.User;
import com.ltdd14.FarmMarket.model.user.UserDao;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserDao userDao;

    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }



    @GetMapping("/user/get-all")
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @PostMapping("/user/save")
    public User save(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user.setRole("ROLE_USER");
        return userDao.save(user);
    }

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody User user) {

        User userAuth = userDao.getUser(user.getUsername());

        if(userAuth == null) {
            return new ResponseEntity<>("Username or Password incorrect", HttpStatus.NOT_FOUND);
        } else {
            if(this.passwordEncoder.matches(user.getPassword(), userAuth.getPassword())){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String token = jwtGenerator.generateToken(authentication);

                String jsonString = "{\"token\":\""+token+"\"}";

                Gson gson = new Gson();
                JSONObject jsonObject = gson.fromJson(jsonString, JSONObject.class);


                return new ResponseEntity<>(jsonObject, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Username or Password incorrect", HttpStatus.BAD_REQUEST);
            }
        }
    }


    @GetMapping(path = "/user/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<User> details(Principal user) {
        User u = this.userDao.getUser(user.getName());

        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
