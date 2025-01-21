package com.youngsoft.knowledgerepo.model;

import com.youngsoft.knowledgerepo.enums.Department;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a user in the Knowledge Sharing Platform (KSP).
 * <p>
 * Stores basic user details such as username, email, phone number, first name, and last name.
 * Timestamps for creation and last update are managed automatically.
 * </p>
 *
 * <p>
 * The username and email are unique, while the phone number is required but not unique.
 * </p>
 */


@Entity
@Table(name = "users")
@Getter
@Setter
public class KspUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)  // Store enum as a string in the database
    @Column(nullable = false)
    private Department department;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int points = 0; // Default points set to 0

    /**
     * Adds points to the user's total points.
     *
     * @param pointsToAdd the number of points to be added.
     */
    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;
    }

    /**
     * Subtracts points from the user's total points.
     *
     * @param pointsToSubtract the number of points to be subtracted.
     */
    public void subtractPoints(int pointsToSubtract) {
        this.points -= pointsToSubtract;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Role> roles; // A user can have multiple roles
}
