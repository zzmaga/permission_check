package com.example.demo.entity.user;

import jakarta.persistence.*;
import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.security.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profiles")
@ToString(exclude = {"user"})
@SuperBuilder
public class UserProfile extends BaseEntity {

    @Column(name = "organization_id")
    private UUID organizationId;

    @Column(name = "department_id")
    private UUID departmentId;

    @Column(name = "is_blocked", nullable = false)
    @Builder.Default
    private Boolean isBlocked = false;

    @Column(name = "specialization_id")
    private UUID specializationId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "photo_path")
    private String photoPath;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_main", nullable = false)
    @Builder.Default
    private Boolean isMain = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}

