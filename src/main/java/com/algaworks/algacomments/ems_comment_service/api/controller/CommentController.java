package com.algaworks.algacomments.ems_comment_service.api.controller;

import com.algaworks.algacomments.ems_comment_service.api.model.CommentInput;
import com.algaworks.algacomments.ems_comment_service.api.model.CommentOutput;
import com.algaworks.algacomments.ems_comment_service.domain.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentOutput createComment(@RequestBody CommentInput input) {
        return commentService.createComment(input);
    }

    @GetMapping("/{id}")
    public CommentOutput getCommentById(@PathVariable String id) {
        return commentService.getCommentById(UUID.fromString(id));
    }

    @GetMapping
    public Page<CommentOutput> getAllComments(Pageable pageable) {
        return commentService.getAllComments(pageable);
    }


}
