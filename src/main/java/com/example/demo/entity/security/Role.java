package com.example.demo.entity.security;

import jakarta.persistence.*;
import com.example.demo.commonlib.model.LocalizedText;
import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.user.UserProfile;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "roles")
@BatchSize(size = 50)
public class Role extends BaseEntity {

    @Column(columnDefinition = "jsonb", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private LocalizedText name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private LocalizedText description;

    @Column(name = "organization_type_id")
    private UUID organizationTypeId;

    @Column(name = "organization_id")
    private UUID organizationId;

    @Column(name = "start_menu_item_code")
    private String startMenuItemCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope_type", nullable = false)
    private ScopeType scopeType;

    @Column(name = "is_visible")
    @Builder.Default
    private Boolean isVisible = true;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<UserProfile> profiles;
}
