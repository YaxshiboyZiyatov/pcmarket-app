package com.example.pcmarketuz.service;

import com.example.pcmarketuz.entity.Article;
import com.example.pcmarketuz.entity.Attachment;
import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.ArticleDto;
import com.example.pcmarketuz.repository.ArticleRepository;
import com.example.pcmarketuz.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

//    public List<Article> getAll(int page, int size){
//        Pageable pageable=PageRequest.of(page, size);
//        Page<Article> all = articleRepository.findAll(pageable);
//        return all.getContent();
//    }
    public List<Article> getAll(){
        return articleRepository.findAll();
    }

    public Article getById(Integer id){
        Optional<Article> byId = articleRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(ArticleDto articleDto){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(articleDto.getAttachmentId());
        boolean exists = articleRepository.existsByUrlLink(articleDto.getUrlLink());
        if (exists){
            return new ApiResponse("This is urlLink already exists", false);
        }
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("Not found Attachment Id", false);
        }
        Article article=new Article();
        article.setTitle(articleDto.getTitle());
        article.setDescription(articleDto.getDescription());
        article.setUrlLink(articleDto.getUrlLink());
        article.setAttachment(optionalAttachment.get());

        articleRepository.save(article);

        return new ApiResponse("saved", true);

    }
    public ApiResponse edit(Integer id, ArticleDto articleDto){
        Optional<Article> optionalArticle = articleRepository.findById(id);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(articleDto.getAttachmentId());
        boolean exists = articleRepository.existsByUrlLink(articleDto.getUrlLink());
        if (exists){
            return new ApiResponse("This is urlLink already exists", false);
        }
        if (!optionalAttachment.isPresent()){
            return new ApiResponse("Not found Attachment Id", false);
        }
        if (!optionalArticle.isPresent()){
            return new ApiResponse("Not found articleId", false);
        }
        Article article = optionalArticle.get();
        article.setTitle(articleDto.getTitle());
        article.setDescription(articleDto.getDescription());
        article.setUrlLink(articleDto.getUrlLink());
        article.setAttachment(optionalAttachment.get());

        articleRepository.save(article);

        return new ApiResponse("saved", true);


    }
    public ApiResponse delete(Integer id){
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()){
            articleRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found id", false);
    }
}
