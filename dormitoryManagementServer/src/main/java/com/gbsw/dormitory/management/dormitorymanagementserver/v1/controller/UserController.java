package com.gbsw.dormitory.management.dormitorymanagementserver.v1.controller;

import com.gbsw.dormitory.management.dormitorymanagementserver.v1.dto.ChangePasswordDto;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.dto.ResponseDto;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.dto.UserLoginDto;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity.UserEntity;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.dto.JwtInfoDto;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("nfcTag")
    @ResponseBody
    public ResponseDto NfcTag(@RequestParam String uuid) {

        ResponseDto res = ResponseDto.builder().status(200).build();
        res.setData(userService.userTag(uuid));
        return res;
    }
    @GetMapping("room-student")
    public ResponseDto getRoomUser(@RequestParam int room) {

        ResponseDto res = ResponseDto.builder().status(200).build();
        res.setData(userService.userListofRoom(room));

        return res;
    }

    @PostMapping("login")
    public ResponseDto login(@RequestBody UserLoginDto req) {
        System.out.println(req.getId());
        ResponseDto res = ResponseDto.builder().status(200).data("").build();

        String id = req.getId();
        String password = req.getPassword();
        JwtInfoDto jwtInfoDto = userService.login(id,  password);

        res.setData(jwtInfoDto);

        return res;
    }

    @GetMapping("/all-student")
    public ResponseDto getAllUser() {

        ResponseDto res = ResponseDto.builder().status(200).build();
        res.setData(userService.userList());

        return res;
    }

    @PostMapping("addUser")
    public ResponseDto addUser(@RequestBody HashMap<String, Object>json) {

        ResponseDto res = ResponseDto.builder().status(200).build();

        UserEntity userEntity = userService.addUser(
            Integer.parseInt(json.get("stu_code").toString()),
            json.get("name").toString(), json.get("uuid").toString(),
            Integer.parseInt(json.get("gender").toString()),
            Integer.parseInt(json.get("room").toString()),
            json.get("password").toString(),
            json.get("id").toString()
        );

        res.setData(userEntity);

        return res;
    }

    @PostMapping("change-password")
    public ResponseDto changePassword(@RequestBody ChangePasswordDto req) {

        ResponseDto res = ResponseDto.builder().status(200).build();
        if (req.getChangePasswordOne().equals(req.getChangePasswordTwo())) {
            res.setData(userService.userChangePassword(req.getId(), req.getChangePasswordOne()));
        } else {
            res.setStatus(500);
//            res.setReason("비밀번호가 서로 다름");
        }
        return res;
    }
}
