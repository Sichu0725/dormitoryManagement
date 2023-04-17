package com.gbsw.dormitory.management.dormitorymanagementserver.v1.repo;

import com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity.NotificationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByOrderByIdxDesc(Pageable pageable);
}
