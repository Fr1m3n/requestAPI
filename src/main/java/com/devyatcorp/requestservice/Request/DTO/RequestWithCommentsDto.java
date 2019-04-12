package com.devyatcorp.requestservice.Request.DTO;

import com.devyatcorp.requestservice.Comment.DTO.CommentDto;
import com.devyatcorp.requestservice.Request.DTO.RequestDto;

import java.util.List;
import java.util.Objects;

public class RequestWithCommentsDto {
    private RequestDto request;
    private List<CommentDto> comments;

    @Override
    public String toString() {
        return "RequestWithCommentsDto{" +
                "record=" + request +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestWithCommentsDto that = (RequestWithCommentsDto) o;
        return Objects.equals(request, that.request) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request, comments);
    }

    public RequestWithCommentsDto(RequestDto record, List<CommentDto> comments) {
        this.request = record;
        this.comments = comments;
    }


    public RequestDto getRecord() {
        return request;
    }

    public void setRecord(RequestDto record) {
        this.request = record;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public RequestWithCommentsDto() {
    }
}
