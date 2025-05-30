package com.algaworks.algacomments.ems_comment_service.api.exception;

public class ModerationRejectedException extends RuntimeException{
    public ModerationRejectedException(String message) {
        super(message);
    }
}

