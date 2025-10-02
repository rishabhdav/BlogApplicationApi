package com.Blog.Repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Entity.Category;
import com.Blog.Entity.Post;
import com.Blog.Entity.User;

public interface PostsRepository extends JpaRepository<Post,String>  {
      Page<Post>findByUser(User user,Pageable p);
    Page<Post>findByCategory(Category category,Pageable p);
    
    
}
