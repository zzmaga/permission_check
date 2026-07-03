package com.example.demo.filter;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import com.example.demo.entity.security.Permission;
import com.example.demo.entity.security.PermissionGroup;
import com.example.demo.validation.PermissionsValidator;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PermissionFilter {
    public static Specification<Permission> build(PermissionsValidator validData) {
        return (entity, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isFalse(entity.get("isDeleted")));

            Join<Permission, PermissionGroup> group = entity.join("group", JoinType.LEFT);
            predicates.add(cb.or(
                    cb.isNull(group.get("id")),
                    cb.isFalse(group.get("isDeleted"))
            ));

            if (StringUtils.hasText(validData.getCode())) {
                predicates.add(cb.like(
                        cb.lower(entity.get("code")),
                        "%" + validData.getCode().toLowerCase() + "%"
                ));
            }

            if (StringUtils.hasText(validData.getName())) {
                String likePattern = "%" + validData.getName().toLowerCase() + "%";

                Predicate kz = cb.like(cb.lower(cb.function(
                        "jsonb_extract_path_text",
                        String.class,
                        entity.get("name"),
                        cb.literal("kk")
                )), likePattern);

                Predicate ru = cb.like(cb.lower(cb.function(
                        "jsonb_extract_path_text",
                        String.class,
                        entity.get("name"),
                        cb.literal("ru")
                )), likePattern);

                Predicate en = cb.like(cb.lower(cb.function(
                        "jsonb_extract_path_text",
                        String.class,
                        entity.get("name"),
                        cb.literal("en")
                )), likePattern);

                predicates.add(cb.or(ru, kz, en));
            }

            if (validData.getGroupId() != null) {
                predicates.add(cb.equal(group.get("id"), validData.getGroupId()));
            }

            if (validData.getIsDelegable() != null) {
                predicates.add(cb.equal(entity.get("isDelegable"), validData.getIsDelegable()));
            }

            if (validData.getIsDangerous() != null) {
                predicates.add(cb.equal(entity.get("isDangerous"), validData.getIsDangerous()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

