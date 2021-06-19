package com.example.pcmarketuz.service;


import com.example.pcmarketuz.entity.Basket;
import com.example.pcmarketuz.entity.Order;
import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.OrderDto;
import com.example.pcmarketuz.repository.BasketRepository;
import com.example.pcmarketuz.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BasketRepository basketRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }


//    public List<Category> getAll(int page, int size){
//        Pageable pageable= PageRequest.of(page, size);
//        Page<Category> all = categoryRepository.findAll(pageable);
//        return all.getContent();
//    }

    public Order getById(Integer id) {
        Optional<Order> byId = orderRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(OrderDto orderDto) {
        Optional<Basket> optionalBasket = basketRepository.findById(orderDto.getBasketId());
        if (!optionalBasket.isPresent()) {
            return new ApiResponse("not found basketId", false);
        }
        Order order = new Order();
        Date date = new Date();
        order.setDate(new Timestamp(date.getTime()));
        order.setDetails(orderDto.getDetails());
        order.setBasket(optionalBasket.get());
        orderRepository.save(order);

        return new ApiResponse("saved", true);

    }

    public ApiResponse edit(Integer id, OrderDto orderDto) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        Optional<Basket> optionalBasket = basketRepository.findById(orderDto.getBasketId());
        if (!optionalOrder.isPresent()) {
            return new ApiResponse("not found orderId", false);
        }
        if (!optionalBasket.isPresent()) {
            return new ApiResponse("not found basketId", false);
        }
        Order order = optionalOrder.get();
        Date date = new Date();
        order.setDate(new Timestamp(date.getTime()));
        order.setDetails(orderDto.getDetails());
        order.setBasket(optionalBasket.get());
        orderRepository.save(order);

        return new ApiResponse("saved", true);


    }

    public ApiResponse delete(Integer id) {
        Optional<Order> optionalCategory = orderRepository.findById(id);
        if (optionalCategory.isPresent()) {
            orderRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found id", false);
    }
}
