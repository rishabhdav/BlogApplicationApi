package com.Blog.Services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Blog.Entity.Category;
import com.Blog.Entity.Post;
import com.Blog.Entity.User;
import com.Blog.Exception.ResourceNotFoundException;
import com.Blog.Helper.CategoryDto;
import com.Blog.Helper.PostDto;
import com.Blog.Helper.PostResponse;
import com.Blog.Helper.UserDto;
import com.Blog.Repository.CategoryRepository;
import com.Blog.Repository.PostsRepository;
import com.Blog.Repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostsRepository postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository uRepository;
    @Autowired
    private CategoryRepository cRepository;

    @Override
 public PostDto createPost(PostDto postDto ,String userId,String categoryId) {

String id=UUID.randomUUID().toString();
postDto.setId(id);
postDto.setAddedDate(new Date());

User user=this.uRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User is not in DB"));
Category category=this.cRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found"));
postDto.setUser(this.modelMapper.map(user, UserDto.class));
postDto.setCategory(this.modelMapper.map(category, CategoryDto.class));

Post post=this.modelMapper.map(postDto, Post.class);
     
 Post sPost=this.postRepo.save(post);

return modelMapper.map(sPost,PostDto.class) ;      

    }

    @Override
    public void deletePost(String id) {
 Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post not exits in DB"));
 this.postRepo.delete(post);

        
        
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber,Integer pageSize) {
        Pageable p=PageRequest.of(pageNumber, pageSize);

     Page<Post> lists=this.postRepo.findAll(p);
      List<Post>list=lists.getContent();
       List<PostDto>fList=list.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
PostResponse postResponse=new PostResponse();
postResponse.setContent(fList);
postResponse.setPageNumber(lists.getNumber());
postResponse.setPageSize(lists.getSize());
postResponse.setTotalElements(lists.getTotalElements());
postResponse.setLastPage(lists.isLast());
postResponse.setTotalPages(lists.getTotalPages());

        return postResponse;
    }

    @Override
    public PostDto getPostById(String id) {
     Post post= this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post not exits in DB"));
PostDto postDto=this.modelMapper.map(post, PostDto.class);

return postDto;
    }

   

    @Override
    public PostResponse getPostsbyUser(String id,Integer pageNumber,Integer pageSize) {
        Pageable p=PageRequest.of(pageNumber, pageSize);
        
User user= this.uRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"));
Page<Post>list=this.postRepo.findByUser(user,p);


 List<PostDto>lists= list.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
PostResponse postResponse=new PostResponse();
postResponse.setContent(lists);
postResponse.setPageNumber(list.getNumber());
postResponse.setPageSize(list.getSize());
postResponse.setLastPage(list.isLast());
postResponse.setTotalElements(list.getTotalElements());
postResponse.setTotalPages(list.getTotalPages());




return  postResponse;

    }

    @Override
    public List<PostDto> searchPost(String id) {
        // TODO Auto-generated method stub
        return null;
    }
 

    @Override 
    public  PostResponse  getPostsByCategory(String id,Integer pageNumber,Integer pageSize) {
        Pageable p=PageRequest.of(pageNumber, pageSize);

Category category= this.cRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category not exits"));

Page<Post>lists=this.postRepo.findByCategory(category,p);

PostResponse postResponse=new PostResponse();


 List<PostDto> list=lists.stream().map((cate)->this.modelMapper.map(cate, PostDto.class)).collect(Collectors.toList());


 
 postResponse.setContent(list);
 postResponse.setLastPage(lists.isLast());
 postResponse.setPageNumber(lists.getNumber());
 postResponse.setTotalElements(lists.getTotalElements());
 postResponse.setPageSize(lists.getSize());
 postResponse.setTotalPages(lists.getTotalPages());

return postResponse;

    }

    @Override
    public PostDto updatePost(PostDto postDto, String id) {
      
Post post =this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post not exits in DB"));
post.setTitle(postDto.getTitle());
post.setContent(postDto.getContent());
post.setImageName(postDto.getImageName());
post.setAddedDate(new Date());
Post updatePost=this.postRepo.save(post);
return this.modelMapper.map(updatePost, PostDto.class);


    }
    


}
