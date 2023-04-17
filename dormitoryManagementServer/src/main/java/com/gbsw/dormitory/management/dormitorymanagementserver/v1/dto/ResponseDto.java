package com.gbsw.dormitory.management.dormitorymanagementserver.v1.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ResponseDto {

    private int status;
    private Object data;
}
