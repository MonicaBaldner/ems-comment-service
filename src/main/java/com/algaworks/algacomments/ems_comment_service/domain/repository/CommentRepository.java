package com.algaworks.algacomments.ems_comment_service.domain.repository;

import com.algaworks.algacomments.ems_comment_service.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}

