package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass // этот класс сам по себе не является отдельной таблицей в базе. Но все его поля должны автоматически появиться в таблицах тех классов, которые от него наследуются
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @JoinColumn(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @JoinColumn(name = "updated_by")
    private UUID updatedBy;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @JoinColumn(name = "deleted_by")
    private UUID deletedBy;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @PrePersist
    protected void prePersist() {
        normalize();
        beforePersist();

        OffsetDateTime now = OffsetDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;

        if (isDeleted == null) {
            isDeleted = false;
        }
    }

    @PreUpdate
    protected void preUpdate() {
        normalize();
        beforeUpdate();

        this.updatedAt = OffsetDateTime.now();
    }

    protected void normalize() {
    }

    protected void beforePersist() {
    }

    protected void beforeUpdate() {
    }
}
