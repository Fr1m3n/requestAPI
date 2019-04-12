package com.devyatcorp.requestservice.Comment;

import com.devyatcorp.requestservice.Comment.DTO.CommentDto;
import com.devyatcorp.requestservice.Request.RequestRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class CommentController {

    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    RequestRepo requestRepo;

    @Autowired
    CommentService commentService;

    @RequestMapping(method = RequestMethod.POST, path = "/comment")
    public ResponseEntity<CommentDto> createNewComment(@RequestBody CommentDto record){
        CommentDto newComment = commentService.create(record.getRequest(), record.getText());
        if(newComment != null) {
            return new ResponseEntity<>(newComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
