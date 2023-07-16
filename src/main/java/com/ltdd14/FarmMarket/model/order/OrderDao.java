package com.ltdd14.FarmMarket.model.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDao {
    @Autowired
    private OrderRepository repository;


    public Order save(Order order){
        return repository.save(order);
    }


    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(orders::add);
        return orders;
    }


    public List<Order> getOrders(Integer user_id) {
        return repository.getOrderByUserId(user_id);
    }

    public void deleteOrderById(int user_id){
        repository.deleteById(user_id);
    }
}
