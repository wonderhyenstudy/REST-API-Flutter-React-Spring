package com.busanit401.restapibootflutterreact.service;

import com.busanit401.restapibootflutterreact.dto.MemberJoinDTO;

public interface APIUserService {
    // 회원 가입
    void register(MemberJoinDTO memberJoinDTO);

    // 아이디 중복 체크 (존재하면 true, 없으면 false)
    boolean checkMid(String mid);
}