package com.personal.story.layer.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.story.layer.application.model.Content;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "article")
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tittle")
    private String tittle;

    @Column(name = "short_content")
    private String shortContent;

    @Column(name = "content")
    @Lob
    @Convert(converter = Content.ContentConverter.class)
    private Content content;

    @Column(name = "code")
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "created_at")
    private long createdAt;

    @Column(name = "is_deleted")
    private int isDeleted;

    @Column(name = "view")
    private long view;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "article_category",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;


}
