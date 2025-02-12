package com.busanit501.teamboot.service;

import com.busanit501.teamboot.dto.CommentsDTO;
import com.busanit501.teamboot.dto.PageResponseDTO;

public interface CommentsService {
    Long createComment(CommentsDTO commentsDTO);
    PageResponseDTO<CommentsDTO> getCommentsByCommunity(Long communityId, int page, int size);
    void updateComment(Long commentId, String content);
    void deleteComment(Long commentId);
}