package com.algaworks.algacomments.ems_comment_service.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModerationRequest {
    private UUID commentId;
    private String text;

}
