package com.example.pcmarketuz.service;


import com.example.pcmarketuz.entity.Attachment;
import com.example.pcmarketuz.entity.Category;
import com.example.pcmarketuz.entity.Product;
import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.ProductDto;
import com.example.pcmarketuz.repository.AttachmentRepository;
import com.example.pcmarketuz.repository.CategoryRepository;
import com.example.pcmarketuz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getById(Integer id){
        Optional<Product> byId = productRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(ProductDto productDto){
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        boolean exists = productRepository.existsByModel(productDto.getModel());
        if (exists){
            return new ApiResponse("This is model already exists", false);
        }
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Not found Category Id", false);
        }
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("Not found attachment Id", false);
        }
        Product product=new Product();
        product.setModel(productDto.getModel());
        product.setPrice(productDto.getPrice());
        product.setCategory(optionalCategory.get());
        product.setAttachment(optionalAttachment.get());
        productRepository.save(product);

        return new ApiResponse("saved", true);

    }
    public ApiResponse edit(Integer id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        boolean exists = productRepository.existsByModel(productDto.getModel());
        if (exists){
            return new ApiResponse("This is model already exists", false);
        }
        else if (!optionalProduct.isPresent()){
            return new ApiResponse("Not found product Id", false);
        }
       else if (!optionalCategory.isPresent()){
            return new ApiResponse("Not found Category Id", false);
        }
       else if (!optionalAttachment.isPresent()){
            return new ApiResponse("Not found attachment Id", false);
        }
        Product product = optionalProduct.get();
        product.setModel(productDto.getModel());
        product.setPrice(productDto.getPrice());
        product.setCategory(optionalCategory.get());
        product.setAttachment(optionalAttachment.get());
        productRepository.save(product);

        return new ApiResponse("saved", true);


    }
    public ApiResponse delete(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found id", false);
    }
}
