package com.devyatcorp.requestservice.Comment;

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

    @RequestMapping(method = RequestMethod.POST, path = "/comment")
    public ResponseEntity<CommentRecord> createNewComment(Principal principal,
                                                         @RequestBody CommentRecord record){
        if(requestRepo.existsById(record.getRequest())) {
            record = commentRepo.save(record);
            logger.info("created new comment with id=" +
                    Long.valueOf(record.getId()).toString() +
                    " attached to request with id=" +
                    Long.valueOf(record.getRequest()).toString() +
                    " and with text = " + record.getText());
            return new ResponseEntity<>(record, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
