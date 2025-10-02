package com.Blog.Exception;

public class ResourceNotFoundException extends RuntimeException {

public ResourceNotFoundException(String message){
super(message);
}
public ResourceNotFoundException(){
    super("Something went wrong");
}

    
}
