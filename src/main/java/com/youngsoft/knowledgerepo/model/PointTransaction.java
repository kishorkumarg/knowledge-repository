package com.youngsoft.knowledgerepo.model;


import com.youngsoft.knowledgerepo.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Represents a transaction involving points in the Knowledge Sharing Platform (KSP).
 * Tracks when points are added or redeemed.
 */
@Entity
@Table(name = "point_transactions")
@Getter
@Setter
public class PointTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private KspUser user; // The user associated with the points transaction

    @Column(nullable = false)
    private int points; // The number of points added or redeemed

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType; // Type of transaction (AWARDED or REDEEMED)

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}
