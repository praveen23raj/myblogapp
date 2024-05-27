package com.blogapp1.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="title", unique = true)
    private String title;
    private String description;
    private String content;
    @OneToMany(mappedBy = "post",orphanRemoval = true,cascade =CascadeType.ALL)
   private List<Comment> comments=new ArrayList<>();
}
