package com.busanit401.restapibootflutterreact.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class APIUserDTO extends User {

    private String mid; // 사용자 ID
    private String mpw; // 사용자 비밀번호

    // 생성자
    public APIUserDTO(String username, String password, Collection<GrantedAuthority> authorities) {
        super(username, password, authorities); // 부모 클래스(User)의 생성자 호출
        this.mid = username;
        this.mpw = password;
    }
}
