package com.busanit401.restapibootflutterreact.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    // 시작 페이지 번호
    private int start;

    // 끝 페이지 번호
    private int end;

    // 이전 페이지 존재 여부
    private boolean prev;

    // 다음 페이지 존재 여부
    private boolean next;

    private List<E> dtoList;
    private Long nextCursor; // ✅ 다음 커서 (다음 페이지 요청 시 사용할 ID)
    private boolean hasNext; // ✅ 다음 데이터 존재 여부

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        if (total <= 0) {
            return;
        }

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.end = (int) (Math.ceil(this.page / 10.0)) * 10;
        this.start = this.end - 9;

        int last = (int) (Math.ceil((total / (double) size)));
        this.end = Math.min(end, last); // 마지막 페이지 번호 조정

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;


    }

    @Builder(builderMethodName = "withAll2")
    public PageResponseDTO(List<E> dtoList, Long nextCursor, boolean hasNext) {
        this.dtoList = dtoList;
        this.nextCursor = nextCursor;
        this.hasNext = hasNext;
    }
}
