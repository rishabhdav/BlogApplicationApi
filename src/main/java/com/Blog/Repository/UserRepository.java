package com.Blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Entity.User;

public interface UserRepository  extends JpaRepository<User,String>{
    
}
