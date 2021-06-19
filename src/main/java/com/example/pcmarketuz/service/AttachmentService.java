package com.example.pcmarketuz.service;

import com.example.pcmarketuz.entity.Attachment;
import com.example.pcmarketuz.entity.AttachmentContent;
import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.repository.AttachmentContentRepository;
import com.example.pcmarketuz.repository.AttachmentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public ApiResponse uploadFile(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment=new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment save = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent=new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());

        attachmentContent.setAttachment(save);
        attachmentContentRepository.save(attachmentContent);
        return new ApiResponse("File saved", true,save.getId());





    }
}
