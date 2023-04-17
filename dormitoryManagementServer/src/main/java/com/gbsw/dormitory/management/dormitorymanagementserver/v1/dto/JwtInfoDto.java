package com.gbsw.dormitory.management.dormitorymanagementserver.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtInfoDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
