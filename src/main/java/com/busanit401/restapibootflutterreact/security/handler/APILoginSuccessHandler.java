package com.busanit401.restapibootflutterreact.security.handler;

import com.busanit401.restapibootflutterreact.domain.APIUser;
import com.busanit401.restapibootflutterreact.util.JWTUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {

        log.info("Login Success!");
        APIUser apiUser = (APIUser) authentication.getPrincipal();
        String mid = apiUser.getMid();

        Map<String, Object> claims = new HashMap<>();
        claims.put("mid", mid);

        // Access Token (1일), Refresh Token (10일) 생성
        String accessToken = jwtUtil.generateToken(claims, 1);
        String refreshToken = jwtUtil.generateToken(claims, 10);

        Gson gson = new Gson();
        Map<String, String> keyMap = Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().println(gson.toJson(keyMap));
    }
}
