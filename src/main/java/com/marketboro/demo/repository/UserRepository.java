package com.marketboro.demo.repository;


import com.marketboro.demo.common.code.UseYn;
import com.marketboro.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserIdAndUseYn(Integer userId, UseYn useYn);

}
