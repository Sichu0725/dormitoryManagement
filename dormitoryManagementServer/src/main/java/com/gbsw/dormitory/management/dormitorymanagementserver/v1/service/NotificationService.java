package com.gbsw.dormitory.management.dormitorymanagementserver.v1.service;

import com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity.NotificationEntity;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    public List<NotificationEntity> notificationList(int page) {

        Pageable pageable = PageRequest.of(page, 10); // 가져올 page 개수
        return notificationRepository.findAllByOrderByIdxDesc(pageable);
    }

    public NotificationEntity notificationByIdx(Long idx) {
        return notificationRepository.findById(idx).get();
    }


    public NotificationEntity addNotification(String title,String contents) {

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setTitle(title);
        notificationEntity.setContents(contents);
        notificationEntity.setCreatedAt(LocalDateTime.now());

        return notificationRepository.save(notificationEntity);
    }

    public Object allNotificationList() {
        return notificationRepository.findAll();
    }
}
