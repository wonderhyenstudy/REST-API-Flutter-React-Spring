package com.busanit401.restapibootflutterreact.security.exception;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class RefreshTokenException extends RuntimeException {

    private final ErrorCase errorCase;

    public enum ErrorCase {
        NO_ACCESS,    // Access Token이 없음
        BAD_ACCESS,   // 잘못된 Access Token
        NO_REFRESH,   // Refresh Token이 없음
        OLD_REFRESH,  // 오래된 Refresh Token
        BAD_REFRESH   // 잘못된 Refresh Token
    }

    // 생성자
    public RefreshTokenException(ErrorCase errorCase) {
        super(errorCase.name());
        this.errorCase = errorCase;
    }

    /**
     * 에러 응답을 HTTP 응답으로 전송
     *
     * @param response HTTP 응답 객체
     */
    public void sendResponseError(HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value()); // HTTP 401 상태 설정
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 응답 Content-Type 설정

        Gson gson = new Gson();
        String responseStr = gson.toJson(Map.of(
                "msg", errorCase.name(),    // 에러 메시지
                "time", new Date()          // 현재 시간
        ));

        try {
            response.getWriter().println(responseStr); // JSON 응답 전송
        } catch (IOException e) {
            throw new RuntimeException("Failed to send error response", e);
        }
    }
}
