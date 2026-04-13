package com.busanit401.restapibootflutterreact.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // Jackson이 좋아하는 기본 생성자
public class MemberJoinDTO {
    private String mid;
    private String mpw;
}