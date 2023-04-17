package com.gbsw.dormitory.management.dormitorymanagementserver.v1.controller;

import com.gbsw.dormitory.management.dormitorymanagementserver.v1.dto.NotificationDto;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.dto.ResponseDto;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("list")
    public ResponseDto listNotify(@RequestParam(defaultValue = "0") int page) {
        System.out.println("fetch notify" + page);
        ResponseDto res = ResponseDto.builder().status(200).build();

        res.setData(notificationService.notificationList(page));
        return res;
    }

    @GetMapping("get-notification")
    public ResponseDto getNotification(@RequestParam Long idx) {
        ResponseDto res = ResponseDto.builder().status(200).build();;

        res.setData(notificationService.notificationByIdx(idx));
        return res;
    }


    @PostMapping("add-notification")
    public ResponseDto addNotification(@RequestBody NotificationDto notification) {
        ResponseDto res = ResponseDto.builder().status(200).build();

        res.setData(notificationService.addNotification(notification.getTitle(), notification.getContents()));
        return res;
    }
}
