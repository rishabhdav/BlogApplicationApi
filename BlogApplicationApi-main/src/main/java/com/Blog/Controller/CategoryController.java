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
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Helper.ApiResponse;
import com.Blog.Helper.CategoryDto;
import com.Blog.Services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto categoryDto2 = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory() {

        List<CategoryDto> list = this.categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryDto>>(list, HttpStatus.FOUND);

    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(  @PathVariable("id") String id) {

        CategoryDto categoryDto = this.categoryService.getCategory(id);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.FOUND);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") String id) {
        this.categoryService.DeleteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted Successfully", true), HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory( @Valid  @RequestBody CategoryDto categoryDto,
            @PathVariable("id") String id) {
       CategoryDto uCategoryDto= this.categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<CategoryDto>(uCategoryDto, HttpStatus.OK);

    }

}
