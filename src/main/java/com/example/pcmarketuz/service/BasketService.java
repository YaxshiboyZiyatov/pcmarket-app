package com.example.pcmarketuz.service;


import com.example.pcmarketuz.entity.Basket;
import com.example.pcmarketuz.entity.OutputProduct;
import com.example.pcmarketuz.entity.User;
import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.BasketDto;
import com.example.pcmarketuz.repository.BasketRepository;
import com.example.pcmarketuz.repository.OutputProductRepository;
import com.example.pcmarketuz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OutputProductRepository outputProductRepository;

    public List<Basket> getAll() {
        return basketRepository.findAll();
    }


//    public List<Category> getAll(int page, int size){
//        Pageable pageable= PageRequest.of(page, size);
//        Page<Category> all = categoryRepository.findAll(pageable);
//        return all.getContent();
//    }

    public Basket getById(Integer id) {
        Optional<Basket> byId = basketRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(BasketDto basketDto) {
        Optional<User> optionalUser = userRepository.findById(basketDto.getUserId());
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(basketDto.getOutputProductId());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("Not found user id", false);
        }
        if (!optionalOutputProduct.isPresent()) {
            return new ApiResponse("Not found outputProduct id", false);
        }
        Basket basket=new Basket();
        basket.setAllSum(basketDto.getAllSum());
        basket.setUser(optionalUser.get());
        basket.setOutputProduct(optionalOutputProduct.get());
        basketRepository.save(basket);

        return new ApiResponse("saved", true);

    }

    public ApiResponse edit(Integer id, BasketDto basketDto) {
        Optional<Basket> optionalBasket = basketRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(basketDto.getUserId());
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(basketDto.getOutputProductId());
        if (!optionalBasket.isPresent()){
            return new ApiResponse("Not fount BasketId", false);
        }
        if (!optionalUser.isPresent()) {
            return new ApiResponse("Not found user id", false);
        }
        if (!optionalOutputProduct.isPresent()) {
            return new ApiResponse("Not found outputProduct id", false);
        }
        Basket basket = optionalBasket.get();
        basket.setAllSum(basketDto.getAllSum());
        basket.setUser(optionalUser.get());
        basket.setOutputProduct(optionalOutputProduct.get());
        basketRepository.save(basket);


        return new ApiResponse("saved", true);


    }

    public ApiResponse delete(Integer id) {
        Optional<Basket> optionalBasket = basketRepository.findById(id);
        if (optionalBasket.isPresent()) {
            basketRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found id", false);
    }
}
