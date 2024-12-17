package com.sparta.deliverypersons.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("생성일")
    protected LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    @Comment("생성자")
    protected UUID createdBy;

    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("수정일")
    protected LocalDateTime updatedAt;

    @LastModifiedBy
    @Column
    @Comment("수정자")
    protected UUID updatedBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("삭제일")
    protected LocalDateTime deletedAt;

    @Column
    @Comment("삭제자")
    protected UUID deletedBy;

    @NotNull
    @Column(nullable = false)
    protected Boolean isDelete = false;

    protected void delete(UUID deletedBy) {
        this.isDelete = true;
        this.deletedAt = LocalDateTime.now();
        this.deletedBy = deletedBy;
    }

}
