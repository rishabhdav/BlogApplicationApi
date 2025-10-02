package com.Blog.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="POSTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    private String id;
    @Column(name="post_title" ,nullable = false ,length = 100)
    private String title;
    @Column(length = 10000)
    private String content;

private String imageName;

private Date addedDate;


@ManyToOne
private Category category;

@ManyToOne
private User user;

@OneToMany(mappedBy = "post",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
private Set<Comment>comments=new HashSet<>();
    
}
