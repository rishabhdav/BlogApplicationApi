package com.Blog.Helper;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

      
private String id;
@NotBlank
@Size(min=4,message = "userName must not less than 4 characters")
private String name;
@Email(message = "Invalid EmailAddress")
@NotBlank(message = "Email should not null")
private String email;
@NotBlank
@Size(min=3,  max=10,message = "password must contain minimun 4 characters and maximun 10 characters")
// @Pattern(regexp = )
private String password;
@NotBlank

private String about;
private Set<CommentDto>comments=new HashSet<>();


}
