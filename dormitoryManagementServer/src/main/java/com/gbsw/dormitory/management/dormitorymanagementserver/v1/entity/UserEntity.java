package com.gbsw.dormitory.management.dormitorymanagementserver.v1.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "User")
public class UserEntity implements UserDetails {

    @Id
    @Column(unique = true)
    private String id;
    private String password;
    private int stu_code; // 학번
    private String name; // 이름
    private boolean is_in; // Y :잔류 중, N :외출 중
    @Column(unique = true)
    private String uuid; // nfc 태크 id 안드로이드의 경우 데이터 추가시 FF~+학번으로 추가
    private int gender; //male : 0, female : 1
    private int room; // 호실

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
