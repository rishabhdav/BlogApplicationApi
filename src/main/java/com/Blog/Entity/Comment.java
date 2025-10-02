package com.Blog.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name="COMMENTS")
@ToString
public class Comment {
    @Id
    private String id;

    private String content;


    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
