package com.youngsoft.knowledgerepo.model;

import com.youngsoft.knowledgerepo.enums.NotificationType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private KspUser user; // The user receiving the notification

    @Column(nullable = false)
    private String message; // The content of the notification

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType; // Type of notification (e.g., UPVOTE, COMMENT)

    @Column(nullable = false)
    private boolean read = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}