package com.devdam.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.Action;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
    Action findBySlugEqualsIgnoreCase(String slug);
    void deleteBySlugEqualsIgnoreCase(String slug);
}
