package com.marketboro.demo.repository;

import com.marketboro.demo.domain.UserPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPointRepository extends JpaRepository<UserPoint, Long> {

	UserPoint findByUserPointId(Integer userPointId);

	Page<UserPoint> findUserPointsByUserIdOrderByRegDateTimeDesc(Integer userId, Pageable pageable);

}
