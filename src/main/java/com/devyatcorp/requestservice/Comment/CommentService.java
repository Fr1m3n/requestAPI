package com.devyatcorp.requestservice.Comment;

import com.devyatcorp.requestservice.Comment.DTO.CommentDto;
import com.devyatcorp.requestservice.Request.RequestRecord;
import com.devyatcorp.requestservice.Request.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    RequestRepo requestRepo;

    public CommentDto create(Long requestId, String text){
        if(!requestRepo.existsById(requestId)) return null;
        CommentRecord commentRecord = new CommentRecord(requestId, text);
        commentRecord = commentRepo.save(commentRecord);

        return new CommentDto(commentRecord);
    }
}
