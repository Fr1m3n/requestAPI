package com.devyatcorp.requestservice.Request;

import com.devyatcorp.requestservice.Comment.CommentRepo;

import com.devyatcorp.requestservice.Request.DTO.RequestDto;
import com.devyatcorp.requestservice.Request.DTO.RequestWithCommentsDto;
import com.devyatcorp.requestservice.Request.DTO.StatusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class RequestController {

    private Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    RequestRepo requestRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    RequestService requestService;

    @RequestMapping(method = RequestMethod.POST, path = "/request")
    public RequestDto createNewRequest(@RequestBody String description) {
        logger.info("created new request record ");
        return requestService.create(description);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/request/{id}")
    public ResponseEntity<RequestWithCommentsDto> getRequestById(@PathVariable(name = "id") Long id) {
        RequestWithCommentsDto requestWithCommentsDto = requestService.getById(id);
        if (requestWithCommentsDto != null) {
            logger.info("requested record with id=" + Long.valueOf(id).toString());
            return new ResponseEntity<>(requestWithCommentsDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/request/{id}")
    public ResponseEntity<RequestWithCommentsDto> deleteRequestById(@PathVariable(name = "id") Long id) {
        RequestWithCommentsDto requestWithCommentsDto = requestService.delete(id);
        if (requestWithCommentsDto != null) {
            return new ResponseEntity<>(requestWithCommentsDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/status/{id}")
    public ResponseEntity<RequestDto> updateStatus(@PathVariable(name = "id") Long id,
                                                   @RequestBody StatusDto status) {
        RequestDto requestDto = requestService.updateStatus(id, status);
        if (requestDto != null) {
            return new ResponseEntity<>(requestDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

