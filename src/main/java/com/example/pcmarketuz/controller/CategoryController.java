package com.example.pcmarketuz.controller;

import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.CategoryDto;
import com.example.pcmarketuz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> get(){
        return ResponseEntity.ok(categoryService.getAllCategory());
//    public HttpEntity<?> getAll(@RequestParam int page, @RequestParam int size){
//        List<Category> all = categoryService.getAll(page, size);
//        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> byId(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping
    public HttpEntity<?> added(@RequestBody CategoryDto categoryDto){
        ApiResponse add = categoryService.add(categoryDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.edit(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);

    }


}
