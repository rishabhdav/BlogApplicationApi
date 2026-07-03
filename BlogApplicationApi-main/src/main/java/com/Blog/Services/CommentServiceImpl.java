package com.Blog.Services;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Entity.Comment;
import com.Blog.Entity.Post;
import com.Blog.Entity.User;
import com.Blog.Exception.ResourceNotFoundException;
import com.Blog.Helper.CommentDto;
import com.Blog.Repository.CommentRepository;
import com.Blog.Repository.PostsRepository;
import com.Blog.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service

public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired 
    private ModelMapper modelMapper;

    @Autowired
private PostsRepository pRepository;


@Autowired
private UserRepository userRepository;
    @Override
    public CommentDto createComment(CommentDto commentDto, String postId, String userId) {

String id=UUID.randomUUID().toString();
commentDto.setId(id);
Comment comment=this.modelMapper.map(commentDto, Comment.class);
Post post=this.pRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post not exist in DB"));
User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not exits in db"));

comment.setPost(post);
comment.setUser(user);
Comment sComment=this.commentRepository.save(comment);
return this.modelMapper.map(sComment, CommentDto.class);

        
    }

    @Override
    @Transactional
    public void DeleteComment(String id) {
        
        try {
        

            
    Comment comment=this.commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("comments not exits in db"));
    System.out.println(comment);
    
     this.commentRepository.delete(comment);  
    } catch (Exception e) {
       e.printStackTrace();
    }

}


    
}
