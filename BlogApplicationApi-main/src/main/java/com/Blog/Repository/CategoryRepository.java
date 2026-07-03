package com.Blog.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Entity.Category;

public interface  CategoryRepository  extends JpaRepository<Category,String>{
    


}
