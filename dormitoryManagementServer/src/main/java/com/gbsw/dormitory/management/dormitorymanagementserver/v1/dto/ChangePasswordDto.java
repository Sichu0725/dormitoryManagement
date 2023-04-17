package com.gbsw.dormitory.management.dormitorymanagementserver.v1.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ChangePasswordDto {
    private String id;
    private String password;
    private String changePasswordOne;
    private String changePasswordTwo;
}
