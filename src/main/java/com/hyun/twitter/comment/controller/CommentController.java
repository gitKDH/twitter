package com.hyun.twitter.comment.controller;

import com.hyun.twitter.comment.dto.CommentDto;
import com.hyun.twitter.comment.entity.Comment;
import com.hyun.twitter.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    public int addComment(@RequestParam Long postId, @RequestParam Long userId, @RequestBody CommentDto commentDto) {
        log.info("createPost by postId:{} userId:{}", postId, userId);

        Comment NewComment = Comment.builder()
                .postId(postId)
                .userId(userId)
                .content(commentDto.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        return commentService.addComment(NewComment);
    }

}
