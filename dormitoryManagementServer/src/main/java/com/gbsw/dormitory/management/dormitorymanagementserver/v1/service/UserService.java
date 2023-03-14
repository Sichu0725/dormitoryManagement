package com.gbsw.dormitory.management.dormitorymanagementserver.v1.service;

import com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity.UserEntity;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity userTag(int stu_code) {
        // 학번을 받으면 그 학번에 해당하는 학생의 is_in 변경
        UserEntity userEntity = userRepository.findById(stu_code).get();

        userEntity.set_in(!userEntity.is_in());
        userRepository.save(userEntity);
        return userEntity;
    }

    public UserEntity addUser(int stu_code, String name, String uuid, int gender, int room) {
        // 유저 추가용 service
        // todo 실제 사용시에는 학생들을 db에 사전에 추가하고 disable
        UserEntity userEntity = new UserEntity(stu_code, name, uuid, gender, room);
        userRepository.save(userEntity);

        return userEntity;
    }
}
