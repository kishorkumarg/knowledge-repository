package com.youngsoft.knowledgerepo.model;


import com.youngsoft.knowledgerepo.enums.ActivityType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private KspUser user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType activityType; // The type of activity (Login, Post, etc.)

    @Column(nullable = false)
    private String description; // Short description of the activity

    @CreationTimestamp
    private LocalDateTime timestamp; // When the activity happened
}
