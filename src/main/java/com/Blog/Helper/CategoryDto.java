package com.Blog.Helper;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDto {
   
private String id;
@NotBlank
@Size(min=4 ,message = "categoryTitle  should more than 4 chracters")
private String categoryTitle;

@NotBlank
@Size(min=5 ,message = "categoryDescription  should more than 5 chracters")
private String categoryDescription;



}
