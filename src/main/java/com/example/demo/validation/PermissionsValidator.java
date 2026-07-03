package com.example.demo.validation;

import com.example.demo.validation.annotation.ValidSortOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Pageable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PermissionsValidator {
    private String code;

    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @JsonProperty("group_id")
    private UUID groupId;

    @JsonProperty("is_delegable")
    private Boolean isDelegable;

    @JsonProperty("is_dangerous")
    private Boolean isDangerous;

    @Min(value = 1, message = "Page must be >= 1")
    private Integer page = 1;

    @Min(value = 1, message = "Size must be >= 1")
    @Max(value = 100, message = "Size must be <= 100")
    private Integer size = 20;

    private String sortBy = "created_at";

    @ValidSortOrder
    private String sortOrder = "desc";

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setSortBy(String sortBy) {
        if (sortBy == null || sortBy.isBlank()) {
            this.sortBy = "created_at";
            return;
        }

        if (!"created_at".equals(sortBy)) {
            throw new IllegalArgumentException("sort_by must be created_at");
        }

        this.sortBy = sortBy;
    }

    public Pageable toPageable() {
        Sort.Direction direction = Sort.Direction.fromString(sortOrder != null ? sortOrder : "desc");

        Sort sort = Sort.by(direction, "createdAt")
                .and(Sort.by(Sort.Direction.ASC, "id"));

        return PageRequest.of(page - 1, size, sort);
    }

    public void setGroup_id(UUID groupId) {
        this.groupId = groupId;
    }

    public void setIs_delegable(Boolean isDelegable) {
        this.isDelegable = isDelegable;
    }

    public void setIs_dangerous(Boolean isDangerous) {
        this.isDangerous = isDangerous;
    }

    public void setSort_by(String sortBy) {
        setSortBy(sortBy);
    }

    public void setSort_order(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
