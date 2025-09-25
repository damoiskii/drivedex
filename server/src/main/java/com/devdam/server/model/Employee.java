package com.devdam.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(name = "employees")
public class Employee {
    @Id
    private Long userId; // PK and FK to users.id

    @Builder.Default
    private Boolean isRegular = false; // Regular employee or supervisor/manager
}
