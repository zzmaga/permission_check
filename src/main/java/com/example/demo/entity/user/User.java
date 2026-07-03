package com.example.demo.entity.user;

import jakarta.persistence.*;
import com.example.demo.commonlib.model.Sex;
import com.example.demo.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "users")
@ToString(exclude = { "currentPassword" })
public class User extends BaseEntity {

    @Column(unique = true)
    private String iin;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    private String patronymic;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isTwoFactorEnable = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_password_id")
    private UserPasswordHistory currentPassword;

    @Column(name = "is_password_change_required", nullable = false)
    @Builder.Default
    private Boolean isPasswordChangeRequired = false;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @BatchSize(size = 20)
    private List<UserProfile> profiles;

    @OneToMany(mappedBy = "user")
    @SQLRestriction("is_deleted = false")
    private List<UserProfile> activeProfiles;
}
