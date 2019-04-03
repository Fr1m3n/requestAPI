package com.devyatcorp.requestservice.Request;

import com.devyatcorp.requestservice.Comment.CommentRecord;

import java.util.List;
import java.util.Objects;

public class RequestWithCommentsRecord {
    private RequestRecord request;
    private List<CommentRecord> comments;

    @Override
    public String toString() {
        return "RequestWithCommentsRecord{" +
                "record=" + request +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestWithCommentsRecord that = (RequestWithCommentsRecord) o;
        return Objects.equals(request, that.request) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request, comments);
    }

    public RequestWithCommentsRecord(RequestRecord record, List<CommentRecord> comments) {
        this.request = record;
        this.comments = comments;
    }

    public RequestRecord getRecord() {
        return request;
    }

    public void setRecord(RequestRecord record) {
        this.request = record;
    }

    public List<CommentRecord> getComments() {
        return comments;
    }

    public void setComments(List<CommentRecord> comments) {
        this.comments = comments;
    }

    public RequestWithCommentsRecord() {
    }
}
