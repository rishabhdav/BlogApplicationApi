package com.Blog.Services;

import com.Blog.Helper.CommentDto;

public interface  CommentService {

    CommentDto createComment(CommentDto commentDto,String postId,String UserId);
    void DeleteComment(String id);

    
}
