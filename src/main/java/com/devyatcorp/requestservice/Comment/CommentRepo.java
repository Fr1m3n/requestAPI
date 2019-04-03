package com.devyatcorp.requestservice.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentRecord, Long> {
    CommentRecord getById(long id);

    List<CommentRecord> getAllByRequest(long request);
}
