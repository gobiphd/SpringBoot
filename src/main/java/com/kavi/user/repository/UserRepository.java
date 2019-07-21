package com.kavi.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kavi.user.eo.UserEO;

@Repository
public interface UserRepository extends JpaRepository<UserEO, Long> {
}
