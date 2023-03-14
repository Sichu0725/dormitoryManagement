package com.gbsw.dormitory.management.dormitorymanagementserver.v1.controller;

import com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity.UserEntity;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("nfcTag")
    @ResponseBody
    public Map NfcTag(@RequestParam(name = "stu_code") int stu_code) {
        Map res = new HashMap<String,Object>();

        res.put("state", 200);
        res.put("user", userService.userTag(stu_code));
        return res;
    }

    @PostMapping("addUser")
    public UserEntity addUser(@RequestBody HashMap<String, Object>json) {
        System.out.println("json : " + json);
        return userService.addUser(3218, "최홍찬", "FAFAFF000001", 0, 205);
    }

}
