package com.Blog.Services;

import java.util.List;

import com.Blog.Helper.CategoryDto;

public interface CategoryService {

CategoryDto createCategory(CategoryDto categoryDto);
CategoryDto updateCategory(CategoryDto categoryDto,String id);
void DeleteCategory(String id);
CategoryDto getCategory(String id);
List<CategoryDto>getAllCategory();

    
} 
