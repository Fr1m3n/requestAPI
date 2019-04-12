package com.devyatcorp.requestservice.Request;


import com.devyatcorp.requestservice.Comment.CommentRepo;
import com.devyatcorp.requestservice.Comment.CommentService;
import com.devyatcorp.requestservice.Comment.DTO.CommentDto;
import com.devyatcorp.requestservice.Request.DTO.RequestDto;
import com.devyatcorp.requestservice.Request.DTO.RequestWithCommentsDto;
import com.devyatcorp.requestservice.Request.DTO.StatusDto;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class RequestTests {

    private RequestDto requestDto;

    @Autowired
    RequestService requestService;

    @Autowired
    RequestRepo requestRepo;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepo commentRepo;

    @Test
    public void createRequestByClass(){
        requestDto = new RequestDto();
        requestDto.setDescription("123test");
        requestDto = requestService.create(requestDto);

        RequestDto compareRequest = new RequestDto(2L, "123test", StatusEnum.NEW);

        assertEquals(requestDto, compareRequest);
    }

    @Test
    public void createRequestByParams(){
        RequestDto compareRequest = new RequestDto(1L, "123test", StatusEnum.NEW);
        assertEquals(requestDto, compareRequest);
    }

    @Before
    public void createRequestDtoBeforeTest(){
        requestDto = requestService.create("123test");
    }

    @Test
    public void createAndDelete(){
        Long id = requestDto.getId();
        requestService.delete(id);
        assertNull(requestRepo.getById(id));
    }

    @Test
    public void createCommentAndGet(){
        CommentDto comment = new CommentDto("test comment 1", requestDto.getId());
        commentService.create(1L, "test comment 1");
        commentService.create(1L, "test comment 2");
        commentService.create(1L, "test comment 3");
        List<CommentDto> comments = new ArrayList<CommentDto>();
        comments.add(new CommentDto("test comment 1", requestDto.getId()));
        comments.add(new CommentDto("test comment 2", requestDto.getId()));
        comments.add(new CommentDto("test comment 3", requestDto.getId()));

        RequestWithCommentsDto serviceGot = requestService.getById(1L);
        RequestWithCommentsDto mustHave = new RequestWithCommentsDto(requestDto, comments);

        assertEquals(mustHave, serviceGot);
    }

    @Test
    public void updateStatus(){
        StatusDto statusDto = new StatusDto(StatusEnum.ACCEPTED);
        assertEquals(requestRepo.getById(requestDto.getId()).getStatus(), StatusEnum.NEW);
        requestService.updateStatus(requestDto.getId(), statusDto);
        assertEquals(requestRepo.getById(requestDto.getId()).getStatus(), StatusEnum.ACCEPTED);
    }

}
