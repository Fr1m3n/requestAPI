package com.devyatcorp.requestservice.Request;

import com.devyatcorp.requestservice.Comment.CommentRecord;
import com.devyatcorp.requestservice.Comment.CommentRepo;
import com.devyatcorp.requestservice.Comment.DTO.CommentDto;
import com.devyatcorp.requestservice.Request.DTO.RequestDto;
import com.devyatcorp.requestservice.Request.DTO.RequestWithCommentsDto;
import com.devyatcorp.requestservice.Request.DTO.StatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    RequestRepo requestRepo;

    @Autowired
    CommentRepo commentRepo;

    public RequestDto create(RequestDto requestDto){
        RequestRecord newRecord = new RequestRecord(requestDto.getDescription());
        newRecord = requestRepo.save(newRecord);
        return new RequestDto(newRecord);
    }

    public RequestDto create(String description){
        RequestRecord newRecord = new RequestRecord(description);
        newRecord = requestRepo.save(newRecord);
        return new RequestDto(newRecord);
    }

    public RequestWithCommentsDto getById(Long id){
        if(!requestRepo.existsById(id)) return null;
        RequestRecord record = requestRepo.getById(id);
        List<CommentDto> comments = commentRepo.getAllByRequest(record.getId()).stream().map(e -> new CommentDto(e.getText(), e.getRequest())).collect(Collectors.toList());
        return new RequestWithCommentsDto(new RequestDto(record), comments);
    }

    public RequestWithCommentsDto delete(Long id){
        if(!requestRepo.existsById(id)) return null;
        RequestRecord record = requestRepo.getById(id);
        List<CommentRecord> comments = commentRepo.getAllByRequest(record.getId());
        RequestWithCommentsDto result = getById(id);
        for (CommentRecord comment: comments) {
            commentRepo.delete(comment);
        }
        requestRepo.delete(record);
        return result;
    }

    public RequestDto updateStatus(Long id, StatusDto status){
        if(!requestRepo.existsById(id)) return null;
        RequestRecord record = requestRepo.getById(id);
        record.setStatus(status.getStatus());
        requestRepo.save(record);
        return new RequestDto(record);
    }
}
