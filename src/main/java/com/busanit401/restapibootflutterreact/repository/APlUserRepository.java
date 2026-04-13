package com.busanit401.restapibootflutterreact.repository;

import com.busanit401.restapibootflutterreact.domain.APIUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APlUserRepository extends JpaRepository<APIUser, String> {
    boolean existsByMid(String mid);
}
