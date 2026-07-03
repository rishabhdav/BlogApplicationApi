package com.Blog.Services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Entity.Category;
import com.Blog.Exception.ResourceNotFoundException;
import com.Blog.Helper.CategoryDto;
import com.Blog.Repository.CategoryRepository;

@Service
public class CategoryServicesImpl  implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void DeleteCategory(String id) {
       Category category= this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category not exits"));
       this.categoryRepo.delete(category);

       

        
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        String id=UUID.randomUUID().toString();
        categoryDto.setId(id);
       Category category= this.modelMapper.map(categoryDto, Category.class);
      
      Category category2= this.categoryRepo.save(category);
      return this.modelMapper.map(category2, CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category>categories=this.categoryRepo.findAll();

      List<CategoryDto>categoryDtos=  categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());

return categoryDtos;
    }

    @Override
    public CategoryDto getCategory(String id) {
     
     Category category=   this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found"));

     return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String id) {
       Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("categorty not exits in DB"));

       category.setCategoryTitle(categoryDto.getCategoryTitle());
       category.setCategoryDescription(categoryDto.getCategoryDescription());
       
   Category sCategory=    this.categoryRepo.save(category);
return this.modelMapper.map(sCategory, CategoryDto.class);


    }
    
}
