package com.gbsw.dormitory.management.dormitorymanagementserver.v1.repo;

import com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {}
