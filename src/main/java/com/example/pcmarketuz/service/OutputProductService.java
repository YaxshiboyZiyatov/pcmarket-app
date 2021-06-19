package com.example.pcmarketuz.service;


import com.example.pcmarketuz.entity.OutputProduct;
import com.example.pcmarketuz.entity.Product;
import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.OutputProductDto;
import com.example.pcmarketuz.repository.OutputProductRepository;
import com.example.pcmarketuz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;

    public List<OutputProduct> getAll() {
        return outputProductRepository.findAll();
    }


//    public List<Category> getAll(int page, int size){
//        Pageable pageable= PageRequest.of(page, size);
//        Page<Category> all = categoryRepository.findAll(pageable);
//        return all.getContent();
//    }

    public OutputProduct getById(Integer id) {
        Optional<OutputProduct> byId = outputProductRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(OutputProductDto outputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Not found product ID", false);
        }
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setProduct(optionalProduct.get());
        outputProductRepository.save(outputProduct);

        return new ApiResponse("saved", true);

    }

    public ApiResponse edit(Integer id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());

        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Not found product ID", false);
        }
        if (!optionalOutputProduct.isPresent()) {
            return new ApiResponse("Not found OutputProduct ID", false);
        }
        OutputProduct outputProduct = optionalOutputProduct.get();

        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setProduct(optionalProduct.get());
        outputProductRepository.save(outputProduct);

        return new ApiResponse("saved", true);


    }

    public ApiResponse delete(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()) {
            outputProductRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found id", false);
    }
}
