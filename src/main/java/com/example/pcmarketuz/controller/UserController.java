package com.example.pcmarketuz.controller;

import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.UserDto;
import com.example.pcmarketuz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public HttpEntity<?> get(){
        return ResponseEntity.ok(userService.getAll());
//    public HttpEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam int size){
//        List<Article> all = articleService.getAll(page, size);
//        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> byId(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    public HttpEntity<?> added(@Valid @RequestBody UserDto userDto){
        ApiResponse add = userService.add(userDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @PathVariable Integer id, @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.edit(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Integer id){
        ApiResponse apiResponse = userService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
