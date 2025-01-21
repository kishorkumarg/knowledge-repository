package com.youngsoft.knowledgerepo.model;

import com.youngsoft.knowledgerepo.enums.AnnouncementType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // Title of the announcement

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // Content of the announcement

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnnouncementType announcementType; // Type of the announcement (e.g., HACKATHON, WEBINAR, TECH_TALK)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private KspUser createdBy; // User who created the announcement

    @CreationTimestamp
    private LocalDateTime createdAt; // Timestamp for when the announcement was created

    @UpdateTimestamp
    private LocalDateTime updatedAt; // Timestamp for when the announcement was last updated

    @Column(nullable = false)
    private boolean active; // Indicates if the announcement is active or not

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "announcement_tags",
            joinColumns = @JoinColumn(name = "announcement_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>(); // Optional tags for categorization

    @Column(nullable = true)
    private LocalDateTime expirationDate; // Optional expiration date for the announcement

    @Column(nullable = true)
    private String bannerImageUrl; // URL of the banner image (e.g., S3 URL)

    // Constructor, getters, setters, etc.
}
