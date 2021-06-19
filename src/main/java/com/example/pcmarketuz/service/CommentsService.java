package com.example.pcmarketuz.service;


import com.example.pcmarketuz.entity.Comments;
import com.example.pcmarketuz.entity.User;
import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.CommentsDto;
import com.example.pcmarketuz.repository.CommentsRepository;
import com.example.pcmarketuz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    UserRepository userRepository;

    public List<Comments> getAll() {
        return commentsRepository.findAll();
    }


//    public List<Category> getAll(int page, int size){
//        Pageable pageable= PageRequest.of(page, size);
//        Page<Category> all = categoryRepository.findAll(pageable);
//        return all.getContent();
//    }

    public Comments getById(Integer id) {
        Optional<Comments> byId = commentsRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(CommentsDto commentsDto) {
        Optional<User> optionalUser = userRepository.findById(commentsDto.getUserId());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("not found user id", false);
        }
        Comments comments = new Comments();
        comments.setBody(commentsDto.getBody());
        comments.setNumberStar(commentsDto.getNumberStar());
        comments.setUser(optionalUser.get());
        commentsRepository.save(comments);

        return new ApiResponse("saved", true);

    }

    public ApiResponse edit(Integer id, CommentsDto commentsDto) {
        Optional<Comments> optionalComments = commentsRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(commentsDto.getUserId());
        if (!optionalComments.isPresent()) {
            return new ApiResponse("not found comments id", false);
        }
        if (!optionalUser.isPresent()) {
            return new ApiResponse("not found user id", false);
        }
        Comments comments = optionalComments.get();
        comments.setBody(commentsDto.getBody());
        comments.setNumberStar(commentsDto.getNumberStar());
        comments.setUser(optionalUser.get());
        commentsRepository.save(comments);

        return new ApiResponse("saved", true);


    }

    public ApiResponse delete(Integer id) {
        Optional<Comments> optionalComments = commentsRepository.findById(id);
        if (optionalComments.isPresent()) {
            commentsRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found id", false);
    }
}
