package com.example.demo.repository;

import com.example.demo.entity.security.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, UUID>, JpaSpecificationExecutor<PermissionGroup> {

    boolean existsByCodeAndIsDeletedFalse(String code);

    Optional<PermissionGroup> findByIdAndIsDeletedFalse(UUID id);
}

