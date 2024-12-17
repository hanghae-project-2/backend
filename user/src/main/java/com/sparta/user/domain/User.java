package com.sparta.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private User(String username, String password, String slackId, UserRole role, Boolean isApproved, Boolean isDelete, String createdBy) {
        this.username = username;
        this.password = password;
        this.slackId = slackId;
        this.role = role;
        this.isApproved = isApproved;
        this.isDelete = isDelete;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
    }

    public static User create(String username, String password, String slackId, UserRole role, String createdBy) {
        return new User(username, password, slackId, role, false, false, createdBy);
    }

    public void approve(UserRole role, String updatedBy) {
        this.isApproved = true;
        this.role = role;
        this.updateBy = updatedBy;
        this.updateAt = LocalDateTime.now();
    }

    public void updatePassword(String newPassword, String updatedBy) {
        this.password = newPassword;
        this.updateBy = updatedBy;
        this.updateAt = LocalDateTime.now();
    }

    public void updateSlackId(String newSlackId, String updatedBy) {
        this.slackId = newSlackId;
        this.updateBy = updatedBy;
        this.updateAt = LocalDateTime.now();
    }

    public void updateRole(UserRole newRole, String updatedBy) {
        this.role = newRole;
        this.updateBy = updatedBy;
        this.updateAt = LocalDateTime.now();
    }

    public void delete(String deletedBy) {
        this.isApproved = false;
        this.isDelete = true;
        this.deleteBy = deletedBy;
        this.deleteAt = LocalDateTime.now();
    }
}
