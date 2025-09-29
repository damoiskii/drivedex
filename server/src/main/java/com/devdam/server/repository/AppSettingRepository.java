package com.devdam.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devdam.server.model.AppSetting;

@Repository
public interface AppSettingRepository extends JpaRepository<AppSetting, Long> {
    AppSetting findByNameEqualsIgnoreCase(String name);
}
