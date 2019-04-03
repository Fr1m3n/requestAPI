package com.devyatcorp.requestservice.Request;

import com.devyatcorp.requestservice.Comment.CommentRecord;
import com.devyatcorp.requestservice.Comment.CommentRepo;
import com.devyatcorp.requestservice.Request.RequestRecord;
import com.devyatcorp.requestservice.Request.RequestRepo;
import com.devyatcorp.requestservice.Request.StatusEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class RequestController {

    private Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    RequestRepo requestRepo;

    @Autowired
    CommentRepo commentRepo;

    @RequestMapping(method = RequestMethod.POST, path = "/request")
    public RequestRecord createNewRequest(Principal principal,
                                          @RequestBody String description){
        RequestRecord record = new RequestRecord(description);
        record.setStatus(StatusEnum.NEW);
        logger.info("created new request record with id=" + Long.valueOf(record.getId()).toString());
        return requestRepo.save(record);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/request")
    public ResponseEntity<RequestWithCommentsRecord> getRequestById(Principal principal,
                                                       @RequestParam(name="id") long id){
        if(requestRepo.existsById(id)) {
            RequestRecord record = requestRepo.getById(id);
            List<CommentRecord> comments = commentRepo.getAllByRequest(id);
            RequestWithCommentsRecord responseRecord = new RequestWithCommentsRecord(record, comments);
            logger.info("requested record with id=" + Long.valueOf(id).toString());
            return new ResponseEntity<>(responseRecord, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/request")
    public RequestRecord deleteRequestById(Principal principal,
                                           @RequestParam(name = "id") long id){
        RequestRecord record = requestRepo.getById(id);
        List<CommentRecord> comments = commentRepo.getAllByRequest(id);
        logger.info("comments count for request with id=" +
                Long.valueOf(id).toString() +
                " is " +
                Integer.valueOf(comments.size()).toString());
        for (CommentRecord comment : comments) {
            logger.info("deleting comment with id=" +
                    Long.valueOf(comment.getId()).toString());
            commentRepo.delete(comment);
        }
        requestRepo.delete(record);
        logger.info("record with id = " + Long.valueOf(record.getId()).toString() + " deleted");
        return record;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/request/status")
    public ResponseEntity<RequestRecord> updateStatus(Principal principal,
                                      @RequestBody RequestRecord data){
        if(requestRepo.existsById(data.getId())){
            RequestRecord record = requestRepo.getById(data.getId());
            record.setStatus(data.getStatus());
            return new ResponseEntity<>(requestRepo.save(record), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
