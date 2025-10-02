package com.Blog.Services;

import java.util.List;

import com.Blog.Helper.PostDto;
import com.Blog.Helper.PostResponse;

public interface PostService {
    PostDto  createPost(PostDto postDto,String userId,String categoryId);
    
    PostDto updatePost(PostDto postDto,String id);

    void deletePost(String id);

     PostResponse getAllPosts(Integer pageNumber,Integer pageSize);


    PostDto getPostById(String id);

   

     PostResponse getPostsbyUser(String id,Integer pageNumber,Integer pageSize);
      PostResponse getPostsByCategory(String id,Integer pageNumber,Integer pageSize);

    List<PostDto>searchPost(String id);
    
    


}
