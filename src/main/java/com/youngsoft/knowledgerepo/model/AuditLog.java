package com.youngsoft.knowledgerepo.model;

import co.elastic.clients.elasticsearch.watcher.ActionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActionType action;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "performed_by", nullable = false)
    private KspUser performedBy;  // The user who performed the action

    @Column(nullable = false, length = 255)
    private String details;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
