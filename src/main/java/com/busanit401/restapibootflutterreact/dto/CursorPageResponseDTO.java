package com.busanit401.restapibootflutterreact.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CursorPageResponseDTO<E> {

    private List<E> dtoList;
    private Long nextCursor; // ✅ 다음 커서 (다음 페이지 요청 시 사용할 ID)
    private boolean hasNext; // ✅ 다음 데이터 존재 여부
    private int total; // ✅ 전체 개수 추가


    @Builder
    public CursorPageResponseDTO(List<E> dtoList, Long nextCursor, boolean hasNext, int total) {
        this.dtoList = dtoList;
        this.nextCursor = nextCursor;
        this.hasNext = hasNext;
        this.total = total;
    }
}