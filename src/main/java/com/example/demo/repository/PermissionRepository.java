package com.example.demo.repository;

import com.example.demo.entity.security.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface PermissionRepository extends JpaRepository<Permission, UUID>, JpaSpecificationExecutor<Permission> {
    Optional<Permission> findByCode(String code);

    @Query("""
        select p
        from Permission p
        join fetch p.group g
        where p.id = :id
          and p.isDeleted = false
          and g.isDeleted = false
        """)
    Optional<Permission> findByIdAndIsDeletedFalse(@Param("id") UUID id);

    Optional<Permission> findByCodeIgnoreCaseAndIsDeletedFalse(String code);

    boolean existsByCodeIgnoreCaseAndIsDeletedFalse(String code);

    List<Permission> findAllByIdInAndIsDeletedFalse(Set<UUID> ids);

    @Query("""
                SELECT DISTINCT p.code
                FROM Role r 
                JOIN r.permissions p 
                WHERE r.id IN :roleIds 
                  AND r.isDeleted = false 
                  AND p.isDeleted = false
            """)
    Set<String> findPermissionCodesByRoleIds(@Param("roleIds") Collection<UUID> roleIds);


    @Query("""
                select p
                from Permission p
                join RolePermission rp on rp.permission.id = p.id
                where rp.role.id = :roleId
                  and p.isDeleted = false
            """)
    List<Permission> findAllByRoleId(@Param("roleId") UUID roleId);

    boolean existsByGroupIdAndIsDeletedFalse(UUID permissionGroupId);
}
