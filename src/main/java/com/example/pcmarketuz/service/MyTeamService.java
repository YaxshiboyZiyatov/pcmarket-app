package com.example.pcmarketuz.service;


import com.example.pcmarketuz.entity.Attachment;
import com.example.pcmarketuz.entity.MyTeam;
import com.example.pcmarketuz.entity.Position;
import com.example.pcmarketuz.payload.ApiResponse;
import com.example.pcmarketuz.payload.MyTeamDto;
import com.example.pcmarketuz.repository.AttachmentRepository;
import com.example.pcmarketuz.repository.MyTeamRepository;
import com.example.pcmarketuz.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyTeamService {
    @Autowired
    MyTeamRepository myTeamRepository;
    @Autowired
    PositionRepository positionRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public List<MyTeam> getAll() {
        return myTeamRepository.findAll();
    }


//    public List<Category> getAll(int page, int size){
//        Pageable pageable= PageRequest.of(page, size);
//        Page<Category> all = categoryRepository.findAll(pageable);
//        return all.getContent();
//    }

    public MyTeam getById(Integer id) {
        Optional<MyTeam> byId = myTeamRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(MyTeamDto myTeamDto) {
        Optional<Position> optionalPosition = positionRepository.findById(myTeamDto.getPositionId());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(myTeamDto.getAttachmentId());
        if (!optionalPosition.isPresent()) {
            return new ApiResponse("Not found position Id", false);
        }

        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Not found attachment Id", false);
        }
        MyTeam myTeam=new MyTeam();
        myTeam.setFullName(myTeamDto.getFullName());
        myTeam.setPosition(optionalPosition.get());
        myTeam.setAttachment(optionalAttachment.get());
        myTeamRepository.save(myTeam);

        return new ApiResponse("saved", true);

    }

    public ApiResponse edit(Integer id, MyTeamDto myTeamDto) {
        Optional<MyTeam> optionalMyTeam = myTeamRepository.findById(id);
        Optional<Position> optionalPosition = positionRepository.findById(myTeamDto.getPositionId());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(myTeamDto.getAttachmentId());
        if (!optionalMyTeam.isPresent())
            return new ApiResponse("Not found MyTeam id", false);
        if (!optionalPosition.isPresent()) {
            return new ApiResponse("Not found position Id", false);
        }
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Not found attachment Id", false);
        }
        MyTeam myTeam = optionalMyTeam.get();
        myTeam.setFullName(myTeamDto.getFullName());
        myTeam.setPosition(optionalPosition.get());
        myTeam.setAttachment(optionalAttachment.get());
        myTeamRepository.save(myTeam);


        return new ApiResponse("saved", true);


    }

    public ApiResponse delete(Integer id) {
        Optional<MyTeam> optionalMyTeam = myTeamRepository.findById(id);
        if (optionalMyTeam.isPresent()) {
            myTeamRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found id", false);
    }
}
