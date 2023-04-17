package com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; // 공지 id

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String contents; // 공지 글 내용

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt; // 공지 글 쓴 시간

}
