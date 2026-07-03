package com.example.demo.entity.security;

import com.example.demo.commonlib.model.LocalizedText;
import com.example.demo.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissions")
@SuperBuilder // умеет заполнять поля родительского класса, @Builder не умеет
public class Permission extends BaseEntity {
    // @ManyToOne много разных разрешений (Permissions) могут относиться к одной группе (PermissionGroup)
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Spring не будет делать лишний SQL-запрос за группой, пока ты явно не вызовешь permission.getGroup().
    @JoinColumn(name = "group_id", nullable = false)
    private PermissionGroup group;

    @Column(columnDefinition = "jsonb", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private LocalizedText name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private LocalizedText description;

    @Column(nullable = false)
    @Builder.Default
    private boolean isDelegable = false;

    @Column(nullable = false)
    @Builder.Default
    private boolean isDangerous = false;
}
