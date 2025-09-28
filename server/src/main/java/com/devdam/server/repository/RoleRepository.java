package com.devdam.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.Role;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameEqualsIgnoreCase(String name);
    Role findBySlugEqualsIgnoreCase(String slug);
    List<Role> findAllByOrderByNameAsc();

    Page<Role> findAllByOrderByNameAsc(Pageable pageable);
    Page<Role> findAllByNameEqualsIgnoreCaseOrderByNameAsc(String name, Pageable pageable);
}
