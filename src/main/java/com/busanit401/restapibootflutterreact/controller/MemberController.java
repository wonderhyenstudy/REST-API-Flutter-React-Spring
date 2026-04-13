package com.busanit401.restapibootflutterreact.controller;

import com.busanit401.restapibootflutterreact.dto.MemberJoinDTO;
import com.busanit401.restapibootflutterreact.service.APIUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final APIUserService apiUserService;

    // 아이디 중복 체크
    // GET /member/check-mid?mid=user1
    @GetMapping("/check-mid")
    public ResponseEntity<String> checkMid(@RequestParam("mid") String mid) {
        log.info("중복 체크 요청 ID: " + mid);

        boolean isDuplicate = apiUserService.checkMid(mid);

        if (isDuplicate) {
            // 중복된 경우 플러터에서 409를 기대함
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 사용 중인 아이디입니다.");
        } else {
            // 사용 가능한 경우 200 OK
            return ResponseEntity.ok("사용 가능한 아이디입니다.");
        }
    }

    // 회원 가입
    // POST /member/register
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody MemberJoinDTO memberJoinDTO) {
        log.info("회원 가입 요청 데이터: " + memberJoinDTO);

        try {
            apiUserService.register(memberJoinDTO);
            return ResponseEntity.ok("회원 가입 성공");
        } catch (Exception e) {
            log.error("회원 가입 중 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}