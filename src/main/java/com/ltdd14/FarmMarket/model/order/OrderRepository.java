package com.ltdd14.FarmMarket.model.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {


    @Query(value = "SELECT * FROM farmmarket.order WHERE user_id = :user_id", nativeQuery = true)
    List<Order> getOrderByUserId(@Param("user_id") Integer user_id);

}
