package com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private int stu_code;
    @Column(nullable = false)
    private short inOut; // 0: 기숙사 입소 1: 기숙사 외출
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

}
