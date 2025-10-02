package com.Blog.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Helper.ApiResponse;
import com.Blog.Helper.PostDto;
import com.Blog.Helper.PostResponse;
import com.Blog.Services.PostService;
@RestController
@RequestMapping("/api/")

public class PostController {
    @Autowired
    private  PostService postService;

@PostMapping("/user/{userId}/category/{categoryId}/post")
public ResponseEntity<PostDto> savedPost(
@RequestBody PostDto postDto,
@PathVariable("userId") String userId,
@PathVariable("categoryId") String categoryId
){
PostDto postDto2=this.postService.createPost(postDto, userId, categoryId);
return new ResponseEntity<PostDto>(postDto2, HttpStatus.OK);


}

//get by Usero

@GetMapping("/user/{userId}/post")
public ResponseEntity<PostResponse>getPostByUser(
@RequestParam(value="pageNumber" ,defaultValue = "1" ,required = false) Integer pageNumber
,@RequestParam(value="pageSize",defaultValue = "3",required = false)  Integer pageSize,

@PathVariable("userId") String id){
    
 PostResponse postResponse=this.postService.getPostsbyUser(id,pageNumber,pageSize);
 return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

  

}
   
@GetMapping("/category/{categoryId}/post")
public ResponseEntity<PostResponse>getPostbyCategory(
@RequestParam(value="pageNumber",defaultValue = "0" ,required = false) Integer pageNumber,
@RequestParam(value="pageSize",defaultValue = "4" ,required = false) Integer pageSize,   

@PathVariable("categoryId") String categoryId){
   PostResponse postResponse=this.postService.getPostsByCategory(categoryId,pageNumber,pageSize);
 return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);


}

@GetMapping("/posts/{postId}")
public ResponseEntity<PostDto>getPostById(@PathVariable("postId") String id){
PostDto postDto=this.postService.getPostById(id);
return new ResponseEntity<PostDto>(postDto, HttpStatus.FOUND);

}

@GetMapping("/posts")
public ResponseEntity<PostResponse>getAllPosts(
    @RequestParam(value="pageNumber" ,defaultValue ="0",required=false) Integer pageNumber,
    @RequestParam(value = "pageSize",defaultValue = "2",required = false) Integer pageSize

){
PostResponse postResponse=this.postService.getAllPosts(pageNumber,pageSize);

return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

}
@DeleteMapping("/post/{postId}")
public ResponseEntity<ApiResponse>deletePostById(@PathVariable("postId") String id){
this.postService.deletePost(id);
return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted Successfully",true), HttpStatus.OK);

}


@PutMapping("post/{postId}")
public ResponseEntity<PostDto>updatePost(@PathVariable("postId") String id,@RequestBody PostDto postDto){
PostDto postDto2=this.postService.updatePost(postDto, id);
return new ResponseEntity<PostDto>(postDto2, HttpStatus.ACCEPTED);

}


}
