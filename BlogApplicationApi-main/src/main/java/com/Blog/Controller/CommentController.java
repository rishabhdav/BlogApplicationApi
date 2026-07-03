package com.Blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Helper.ApiResponse;
import com.Blog.Helper.CommentDto;
import com.Blog.Services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    
    @PostMapping("/post/{postId}/user/{userId}/comment")
private ResponseEntity<CommentDto>savedComment(@PathVariable("postId") String postId,@PathVariable("userId") String userId,@RequestBody CommentDto commentDto){
CommentDto commentDto1=this.commentService.createComment(commentDto, postId, userId);

return new ResponseEntity<CommentDto>(commentDto1, HttpStatus.OK);

}

@DeleteMapping("/comment/{id}")
private ResponseEntity<ApiResponse>deleteComment(@PathVariable String id){
this.commentService.DeleteComment(id);

return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully",true), HttpStatus.OK);

}
}
