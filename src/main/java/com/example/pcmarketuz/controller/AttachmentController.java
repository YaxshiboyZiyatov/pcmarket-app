package com.example.pcmarketuz.controller;

import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping
    public ApiResponse add(MultipartHttpServletRequest request){
      return attachmentService.uploadFile(request);
    }
}
