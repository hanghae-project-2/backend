package com.sparta.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "username", length = 100, nullable = false)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "slack_id", length = 100)
    private String slackId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete;

    @Column(name = "delete_by", length = 100)
    private String deleteBy;

    @Column(name = "delete_at")
    private LocalDateTime deleteAt;

    @Column(name = "update_by", length = 100)
    private String updateBy;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }

}