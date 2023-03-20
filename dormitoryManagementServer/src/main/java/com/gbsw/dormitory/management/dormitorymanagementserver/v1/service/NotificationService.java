package com.gbsw.dormitory.management.dormitorymanagementserver.v1.service;

import com.gbsw.dormitory.management.dormitorymanagementserver.v1.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
}
