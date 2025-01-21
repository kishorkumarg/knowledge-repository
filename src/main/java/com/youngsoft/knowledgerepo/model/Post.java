package com.youngsoft.knowledgerepo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a Post in the Knowledge Sharing Platform (KSP).
 * Each post can have a title, content, tags, and associated images.
 */
@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // The title of the post

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // The content of the post

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) // Cascading persist operation to Category
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Category to which the post belongs

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>(); // Using Set to avoid duplicates

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private KspUser user; // The user who created the post

    @ElementCollection
    @CollectionTable(name = "post_images", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image_url")
    private List<String> imageUrls; // List of S3 image URLs associated with the post

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // Timestamp when the post was created

    @UpdateTimestamp
    private LocalDateTime updatedAt; // Timestamp when the post was last updated

}
