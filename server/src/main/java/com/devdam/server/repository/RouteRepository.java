package com.devdam.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.Route;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByNameEqualsIgnoreCase(String name);

    List<Route> findByStartPointEqualsIgnoreCase(String startPoint);
    List<Route> findByEndPointEqualsIgnoreCase(String endPoint);

    Page<Route> findAllByNameContainsIgnoringCaseOrStartPointContainsIgnoringCaseOrEndPointContainsIgnoringCase(String name, String startPoint, String endPoint, Pageable pageable);
    Page<Route> findAllByFareLessThanEqual(Double fare, Pageable pageable);
}
