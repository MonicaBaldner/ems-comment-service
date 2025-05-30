package com.algaworks.algacomments.ems_comment_service.domain.service;

import com.algaworks.algacomments.ems_comment_service.api.client.ModerationClient;
import com.algaworks.algacomments.ems_comment_service.api.exception.ModerationRejectedException;
import com.algaworks.algacomments.ems_comment_service.api.model.CommentInput;
import com.algaworks.algacomments.ems_comment_service.api.model.CommentOutput;
import com.algaworks.algacomments.ems_comment_service.api.model.ModerationRequest;
import com.algaworks.algacomments.ems_comment_service.api.model.ModerationResponse;
import com.algaworks.algacomments.ems_comment_service.domain.exception.CommentNotFoundException;
import com.algaworks.algacomments.ems_comment_service.domain.model.Comment;
import com.algaworks.algacomments.ems_comment_service.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModerationClient moderationClient;

    public CommentOutput createComment(CommentInput input) {
        Comment comment = Comment.builder()
                .text(input.getText())
                .author(input.getAuthor())
                .build();

        ModerationRequest request = new ModerationRequest(comment.getId(), input.getText());
        ModerationResponse response = moderationClient.moderateComment(request);

        if (!response.isApproved()) {
            throw new ModerationRejectedException(response.getReason());
        }

        Comment savedComment = commentRepository.saveAndFlush(comment);
        return mapToOutput(savedComment);
    }

    public CommentOutput getCommentById(UUID id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
        return mapToOutput(comment);
    }

    public Page<CommentOutput> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable)
                .map(this::mapToOutput);
    }

    private CommentOutput mapToOutput(Comment comment) {
        return CommentOutput.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .build();
    }


}
