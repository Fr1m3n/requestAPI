package com.devyatcorp.requestservice.Comment.DTO;

import com.devyatcorp.requestservice.Comment.CommentRecord;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

public class CommentDto {
    private String text;
    private Long request;

    public CommentDto(CommentRecord record){
        this.text = record.getText();
        this.request = record.getRequest();
    }

    public CommentDto(String text, Long request) {
        this.text = text;
        this.request = request;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "text='" + text + '\'' +
                ", request=" + request +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return Objects.equals(text, that.text) &&
                Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, request);
    }

    public CommentDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getRequest() {
        return request;
    }

    public void setRequest(Long request) {
        this.request = request;
    }
}
