package com.example.pcmarketuz.controller;

import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.CommentsDto;
import com.example.pcmarketuz.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/comments")
public class CommentsController {
    @Autowired
    CommentsService commentsService;

    @GetMapping
    public HttpEntity<?> get(){
        return ResponseEntity.ok(commentsService.getAll());
//    public HttpEntity<?> getAll(@RequestParam int page, @RequestParam int size){
//        List<Category> all = categoryService.getAll(page, size);
//        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> byId(@PathVariable Integer id){
        return ResponseEntity.ok(commentsService.getById(id));
    }

    @PostMapping
    public HttpEntity<?> added(@RequestBody CommentsDto commentsDto){
        ApiResponse add = commentsService.add(commentsDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody CommentsDto commentsDto){
        ApiResponse apiResponse = commentsService.edit(id, commentsDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Integer id){
        ApiResponse apiResponse = commentsService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);

    }


}
