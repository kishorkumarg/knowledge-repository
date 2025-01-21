package com.youngsoft.knowledgerepo.model;

import com.youngsoft.knowledgerepo.enums.RoleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a role assigned to a user in the Knowledge Sharing Platform (KSP).
 * The role defines the permissions of a user (e.g., Admin, User, Moderator).
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleName name; // Role name (Admin, User, Moderator)

    @ManyToOne(cascade = CascadeType.PERSIST) // Only persist the user when the role is persisted
    @JoinColumn(name = "user_id", nullable = false) // Foreign key linking to KspUser
    private KspUser user; // The user to whom the role is assigned

    /**
     * Default constructor for Role.
     * @param name The role assigned to the user (e.g., ADMIN, USER)
     * @param user The user who has this role
     */
    public Role(RoleName name, KspUser user) {
        this.name = name;
        this.user = user;
    }
}