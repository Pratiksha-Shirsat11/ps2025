package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    //many belongs to comments and one belongs to post
    //first word belongs to class like many belongs to comments

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}