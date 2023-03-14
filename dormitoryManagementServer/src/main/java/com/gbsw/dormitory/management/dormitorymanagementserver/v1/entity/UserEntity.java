package com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "User")
public class UserEntity {
    @Id
    private int stu_code; // 학번
    private boolean is_in; // Y :잔류 중, N :외출 중
    private String name; // 이름
    private String uuid; // nfc 태크 id 안드로이드의 경우 데이터 추가시 FF~+학번으로 추가
    private int gender; //male : 0, female : 1
    private int room; // 호실
    public UserEntity(int stu_code, String name, String uuid, int gender, int room) {
        this.gender = gender;
        this.name = name;
        this.stu_code = stu_code;
        this.uuid = uuid;
        this.room = room;
    }


}
