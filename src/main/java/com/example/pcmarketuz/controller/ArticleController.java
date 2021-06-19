package com.example.pcmarketuz.controller;

import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.ArticleDto;
import com.example.pcmarketuz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping
    public HttpEntity<?> get(){
        return ResponseEntity.ok(articleService.getAll());
//    public HttpEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam int size){
//        List<Article> all = articleService.getAll(page, size);
//        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> byId(@PathVariable Integer id){
        return ResponseEntity.ok(articleService.getById(id));
    }

    @PostMapping
    public HttpEntity<?> added(@RequestBody ArticleDto articleDto){
        ApiResponse add = articleService.add(articleDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ArticleDto articleDto){
        ApiResponse apiResponse = articleService.edit(id, articleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Integer id){
        ApiResponse apiResponse = articleService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);

    }


}
