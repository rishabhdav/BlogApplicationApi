package com.Blog.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Category {
    
 @Id
private String id;
@Column(name="title",length=100,nullable = false)
private String categoryTitle;

@Column(name="decription")
private String categoryDescription;

@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
private List<Post>posts=new ArrayList<>();

}
