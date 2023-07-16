package com.ltdd14.FarmMarket.controller;

import com.ltdd14.FarmMarket.model.order.Order;
import com.ltdd14.FarmMarket.model.order.OrderDao;
import com.ltdd14.FarmMarket.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderDao orderDao;

    @GetMapping("/order/get-all")
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @GetMapping("/order/{user_id}")
    public List<Order> getOrder(@PathVariable Integer user_id) {

        List<Order> orders = orderDao.getOrders(user_id);


        return orders;
    }


}
