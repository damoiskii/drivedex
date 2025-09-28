// package com.devdam.server.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.time.LocalDateTime;
// import java.util.Date;
// import java.util.List;

// @Repository
// public interface APITokenRepository extends JpaRepository<APIToken, Long> {
//     APIToken findByTokenEqualsIgnoreCase(String token);
//     List<APIToken> findAllByExpiringBefore(LocalDateTime expiring);
// }
