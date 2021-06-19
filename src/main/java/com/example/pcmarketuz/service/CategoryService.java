package com.example.pcmarketuz.service;


import com.example.pcmarketuz.entity.Category;
import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.CategoryDto;
import com.example.pcmarketuz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        List<Category> all = categoryRepository.findAll();
        return all;
    }


//    public List<Category> getAll(int page, int size){
//        Pageable pageable= PageRequest.of(page, size);
//        Page<Category> all = categoryRepository.findAll(pageable);
//        return all.getContent();
//    }

    public Category getById(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists){
            return new ApiResponse("This is name already exists", false);
        }
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Not found Category Id", false);
        }
        Category category=new Category();
        category.setName(categoryDto.getName());
        category.setParentCategory(optionalCategory.get());
        categoryRepository.save(category);

        return new ApiResponse("saved", true);

    }
    public ApiResponse edit(Integer id, CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists){
            return new ApiResponse("This is name already exists", false);
        }
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Not found Category Id", false);
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setParentCategory(optionalCategory.get());
        categoryRepository.save(category);

        return new ApiResponse("saved", true);


    }
    public ApiResponse delete(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            categoryRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found id", false);
    }
}
