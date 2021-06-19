package com.example.pcmarketuz.controller;

import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.CharacteristicsDto;
import com.example.pcmarketuz.service.CharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/characteristics")
public class CharacteristicsController {
    @Autowired
    CharacteristicsService characteristicsService;

    @GetMapping
    public HttpEntity<?> get(){
        return ResponseEntity.ok(characteristicsService.getAll());
//    public HttpEntity<?> getAll(@RequestParam int page, @RequestParam int size){
//        List<Category> all = categoryService.getAll(page, size);
//        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> byId(@PathVariable Integer id){
        return ResponseEntity.ok(characteristicsService.getById(id));
    }

    @PostMapping
    public HttpEntity<?> added(@RequestBody CharacteristicsDto characteristicsDto){
        ApiResponse add = characteristicsService.add(characteristicsDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody CharacteristicsDto characteristicsDto){
        ApiResponse apiResponse = characteristicsService.edit(id, characteristicsDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Integer id){
        ApiResponse apiResponse = characteristicsService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);

    }


}
