package com.gbsw.dormitory.management.dormitorymanagementserver.v1.repo;

import com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findAllByRoom(int room);
    List<UserEntity> findByUuid(String uuid);


}
