package com.example.pcmarketuz.service;


import com.example.pcmarketuz.entity.Characteristics;
import com.example.pcmarketuz.entity.Details;
import com.example.pcmarketuz.entity.Product;
import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.CharacteristicsDto;
import com.example.pcmarketuz.repository.CharacteristicsRepository;
import com.example.pcmarketuz.repository.DetailsRepository;
import com.example.pcmarketuz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacteristicsService {
    @Autowired
    CharacteristicsRepository characteristicsRepository;
    @Autowired
    DetailsRepository detailsRepository;
    @Autowired
    ProductRepository productRepository;

    public List<Characteristics> getAll() {
        return characteristicsRepository.findAll();
    }


//    public List<Category> getAll(int page, int size){
//        Pageable pageable= PageRequest.of(page, size);
//        Page<Category> all = categoryRepository.findAll(pageable);
//        return all.getContent();
//    }

    public Characteristics getById(Integer id) {
        Optional<Characteristics> byId = characteristicsRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(CharacteristicsDto characteristicsDto) {
        Optional<Details> optionalDetails = detailsRepository.findById(characteristicsDto.getDetailsId());
        Optional<Product> optionalProduct = productRepository.findById(characteristicsDto.getProductId());
        if (!optionalDetails.isPresent()) {
            return new ApiResponse("not found details id", false);
        }
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("not found product id", false);
        }
        Characteristics characteristics=new Characteristics();
        characteristics.setValue(characteristicsDto.getValue());
        characteristics.setDetails(optionalDetails.get());
        characteristics.setProduct(optionalProduct.get());
        characteristicsRepository.save(characteristics);

        return new ApiResponse("saved", true);

    }

    public ApiResponse edit(Integer id, CharacteristicsDto characteristicsDto) {
        Optional<Characteristics> optionalCharacteristics = characteristicsRepository.findById(id);
        Optional<Details> optionalDetails = detailsRepository.findById(characteristicsDto.getDetailsId());
        Optional<Product> optionalProduct = productRepository.findById(characteristicsDto.getProductId());
        if (!optionalCharacteristics.isPresent()){
            return new ApiResponse("Not found Characteristics id", false);
        }
        if (!optionalDetails.isPresent()) {
            return new ApiResponse("not found details id", false);
        }
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("not found product id", false);
        }
        Characteristics characteristics = optionalCharacteristics.get();
        characteristics.setValue(characteristicsDto.getValue());
        characteristics.setDetails(optionalDetails.get());
        characteristics.setProduct(optionalProduct.get());
        characteristicsRepository.save(characteristics);

        return new ApiResponse("saved", true);


    }

    public ApiResponse delete(Integer id) {
        Optional<Characteristics> optionalCharacteristics = characteristicsRepository.findById(id);
        if (optionalCharacteristics.isPresent()) {
            characteristicsRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found id", false);
    }
}
