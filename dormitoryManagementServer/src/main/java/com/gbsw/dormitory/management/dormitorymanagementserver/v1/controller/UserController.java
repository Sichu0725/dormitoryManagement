package com.gbsw.dormitory.management.dormitorymanagementserver.v1.controller;

import com.gbsw.dormitory.management.dormitorymanagementserver.v1.dto.UserLoginDto;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity.UserEntity;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.security.TokenInfo;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("nfcTag")
    @ResponseBody
    public Map NfcTag(@RequestParam String uuid) {
        Map res = new HashMap<String,Object>();

        res.put("status", 200);
        res.put("response", userService.userTag(uuid));
        return res;
    }
    @GetMapping("room-student")
    public Map getRoomUser(@RequestParam int room) {

        Map res = new HashMap<String,Object>();

        res.put("status", 200);
        res.put("response", userService.userListofRoom(room));

        return res;
    }

    @PostMapping("login")
    public Map login(@RequestBody UserLoginDto userLoginDto) {
        Map res = new HashMap<String, Object>();

        String id = userLoginDto.getId();
        String password = userLoginDto.getPassword();
        TokenInfo tokenInfo = userService.login(id, password);

        res.put("status", 200);
        res.put("response", tokenInfo);

        return res;
    }

    @GetMapping("/all-student")
    public Map getAllUser() {

        Map res = new HashMap<String,Object>();

        res.put("status", 200);
        res.put("response", userService.userList());

        return res;
    }

    @PostMapping("addUser")
    public Map addUser(@RequestBody HashMap<String, Object>json) {
        HashMap<String, Object> res = new HashMap<>();

        res.put("status", 200);
        res.put("response", userService.addUser(Integer.parseInt(json.get("stu_code").toString()), json.get("name").toString(), json.get("uuid").toString(), Integer.parseInt(json.get("gender").toString()), Integer.parseInt(json.get("room").toString()), json.get("password").toString(), json.get("id").toString()));

        return res;
    }
}
