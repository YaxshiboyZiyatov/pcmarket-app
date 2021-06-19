package com.example.pcmarketuz.controller;

import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.MyTeamDto;
import com.example.pcmarketuz.service.MyTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/myTeam")
public class MyTeamController {
    @Autowired
    MyTeamService myTeamService;

    @GetMapping
    public HttpEntity<?> get(){
        return ResponseEntity.ok(myTeamService.getAll());
//    public HttpEntity<?> getAll(@RequestParam int page, @RequestParam int size){
//        List<Category> all = categoryService.getAll(page, size);
//        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> byId(@PathVariable Integer id){
        return ResponseEntity.ok(myTeamService.getById(id));
    }

    @PostMapping
    public HttpEntity<?> added(@RequestBody MyTeamDto myTeamDto){
        ApiResponse add = myTeamService.add(myTeamDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody MyTeamDto myTeamDto){
        ApiResponse apiResponse = myTeamService.edit(id, myTeamDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Integer id){
        ApiResponse apiResponse = myTeamService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);

    }


}
