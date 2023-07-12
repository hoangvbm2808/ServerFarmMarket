package com.ltdd14.FarmMarket.model.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT username FROM user WHERE username = :username", nativeQuery = true)
    String checkUsername(@Param("username") String username);

    @Query(value = "SELECT password FROM user WHERE username = :username", nativeQuery = true)
    String checkPassword(@Param("username") String username);

    @Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
    User getUserByUsername(@Param("username") String username);
}
