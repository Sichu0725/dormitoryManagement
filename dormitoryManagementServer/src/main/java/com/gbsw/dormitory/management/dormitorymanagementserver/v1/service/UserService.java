package com.gbsw.dormitory.management.dormitorymanagementserver.v1.service;

import com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity.UserEntity;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.repo.UserRepository;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.security.JwtProvider;
import com.gbsw.dormitory.management.dormitorymanagementserver.v1.dto.JwtInfoDto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtInfoDto login(String id, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);
    }

    public UserEntity userTag(String uuid) {
        // 학번을 받으면 그 학번에 해당하는 학생의 is_in 변경
        UserEntity userEntity = userRepository.findByUuid(uuid).get(0);

        userEntity.set_in(!userEntity.is_in());
        userRepository.save(userEntity);
        return userEntity;
    }

    public List<UserEntity> userList() {
        // 호실 기준 오름차순으로 모든 학생리스트 반환

        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "room"));
    }

    public List<UserEntity> userListofRoom(int room) {
        return userRepository.findAllByRoom(room);
    }
    public UserEntity addUser(int stu_code, String name, String uuid, int gender, int room, String password, String id) {
        // todo 실제 사용시에는 학생들을 db에 사전에 추가하고 disable
        UserEntity userEntity = new UserEntity();
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        // stu_code, name, uuid, gender, room, passwordEncoder.encode(password)
        userEntity.setStu_code(stu_code);
        userEntity.setName(name);
        userEntity.setGender(gender);
        userEntity.setRoom(room);
        userEntity.setUuid(uuid);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setRoles(roles);
        userEntity.setId(id);

        userRepository.save(userEntity);

        return userEntity;
    }

    public UserEntity userChangePassword(String id, String password) {

        UserEntity user = userRepository.findById(id).get();
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.saveAndFlush(user);

    }


}