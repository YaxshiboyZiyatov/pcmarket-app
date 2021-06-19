package com.example.pcmarketuz.service;

import com.example.pcmarketuz.entity.Attachment;
import com.example.pcmarketuz.entity.User;
import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.UserDto;
import com.example.pcmarketuz.repository.AttachmentRepository;
import com.example.pcmarketuz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

//    public List<Article> getAll(int page, int size){
//        Pageable pageable=PageRequest.of(page, size);
//        Page<Article> all = articleRepository.findAll(pageable);
//        return all.getContent();
//    }
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Integer id){
        Optional<User> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(UserDto userDto){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(userDto.getAttachmentId());
        boolean exists = userRepository.existsByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        if (exists){
            return new ApiResponse("this are elements already exists", false);
        }
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("Not found Attachment Id", false);
        }
        User user=new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAttachment(optionalAttachment.get());
        userRepository.save(user);

        return new ApiResponse("saved", true);

    }
    public ApiResponse edit(Integer id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(userDto.getAttachmentId());
        boolean exists = userRepository.existsByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        if (exists){
            return new ApiResponse("this are elements already exists", false);
        }
        if (!optionalUser.isPresent()){
            return new ApiResponse("Not found userId", false);
        }
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("Not found Attachment Id", false);
        }

        User user = optionalUser.get();

        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAttachment(optionalAttachment.get());
        userRepository.save(user);

        return new ApiResponse("saved", true);

    }
    public ApiResponse delete(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found id", false);
    }
}
