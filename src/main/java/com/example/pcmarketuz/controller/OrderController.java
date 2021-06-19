package com.example.pcmarketuz.controller;

import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.OrderDto;
import com.example.pcmarketuz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PreAuthorize(value = "hasAuthority('READ_ALL_ORDER')")
    @GetMapping
    public HttpEntity<?> get(){
        return ResponseEntity.ok(orderService.getAll());
//    public HttpEntity<?> getAll(@RequestParam int page, @RequestParam int size){
//        List<Category> all = categoryService.getAll(page, size);
//        return ResponseEntity.ok(all);
    }
    @PreAuthorize(value = "hasAuthority('READ_ONE_ORDER')")
    @GetMapping("/{id}")
    public HttpEntity<?> byId(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PreAuthorize(value = "hasAuthority('ADD_ORDER')")
    @PostMapping
    public HttpEntity<?> added(@RequestBody OrderDto orderDto){
        ApiResponse add = orderService.add(orderDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody OrderDto orderDto){
        ApiResponse apiResponse = orderService.edit(id, orderDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize(value = "hasAuthority('DETELE_ORDER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Integer id){
        ApiResponse apiResponse = orderService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);

    }


}
