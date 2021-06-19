package com.example.pcmarketuz.controller;

import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.ProductDto;
import com.example.pcmarketuz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize(value = "hasAnyAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public HttpEntity<?> getProduct() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PreAuthorize(value = "hasAuthority('READ_ONE_PRODUCT')")
    @GetMapping("/{id}")
    public HttpEntity<?> byId(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public HttpEntity<?> added(@Valid @RequestBody ProductDto productDto) {
        ApiResponse add = productService.add(productDto);
        return ResponseEntity.status(add.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(add);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @PathVariable Integer id, @RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.edit(id, productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Integer id) {
        ApiResponse apiResponse = productService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);

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
