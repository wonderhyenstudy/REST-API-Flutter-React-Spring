package com.busanit401.restapibootflutterreact.service;

import com.busanit401.restapibootflutterreact.domain.APIUser;
import com.busanit401.restapibootflutterreact.dto.MemberJoinDTO;
import com.busanit401.restapibootflutterreact.repository.APlUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class APIUserServiceImpl implements APIUserService {

    private final APlUserRepository apiUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(MemberJoinDTO memberJoinDTO) {
        // 이미 존재하는 아이디인지 한 번 더 확인 (방어 코드)
        if(apiUserRepository.existsByMid(memberJoinDTO.getMid())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        APIUser user = APIUser.builder()
                .mid(memberJoinDTO.getMid())
                .mpw(passwordEncoder.encode(memberJoinDTO.getMpw())) // 비밀번호 암호화
                .build();

        apiUserRepository.save(user);
        log.info("회원 가입 완료: " + user.getMid());
    }

    @Override
    public boolean checkMid(String mid) {
        return apiUserRepository.existsByMid(mid);
    }
}