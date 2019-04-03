package com.devyatcorp.requestservice.Comment;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class CommentRecord {

    @Id
    @GeneratedValue
    private long id;

    // id заявки к которой относится комментарий
    @Column(name = "request")
    private long request;

    // содержание комментария
    @Column(name = "text")
    private String text;

    public CommentRecord(long request, String text) {
        this.request = request;
        this.text = text;
    }

    @Override
    public String toString() {
        return "CommentRecord{" +
                "id=" + id +
                ", request=" + request +
                ", text='" + text + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRequest() {
        return request;
    }

    public void setRequest(long request) {
        this.request = request;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CommentRecord() {
    }
}
