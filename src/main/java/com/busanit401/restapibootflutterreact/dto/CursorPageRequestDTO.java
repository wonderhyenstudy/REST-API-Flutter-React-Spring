package com.busanit401.restapibootflutterreact.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursorPageRequestDTO {

    @Builder.Default
    private int size = 10;  // ✅ 한 번에 불러올 데이터 개수

    private Long cursor;  // ✅ 커서 기반 페이지네이션 (이전 데이터의 마지막 tno)

    private String type;   // 검색 종류 (T, C, W, TC, TWC 등)
    private String keyword;

    // ✅ 추가된 필드 (기간 검색, 완료 여부)
    private LocalDate from;
    private LocalDate to;
    private Boolean completed;

    public String[] getTypes() {
        if (type == null || type.isEmpty()) {
            return new String[]{};
        }
        return type.split(""); // 한 글자씩 분리
    }

    public Pageable getPageable(String... props) {
        return PageRequest.of(0, this.size, Sort.by(props).descending());
    }

    private String link;

    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("size=").append(this.size);

            if (cursor != null) {
                builder.append("&cursor=").append(cursor);
            }

            if (type != null && !type.isEmpty()) {
                builder.append("&type=").append(type);
            }

            if (keyword != null) {
                try {
                    builder.append("&keyword=").append(URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("Encoding error", e);
                }
            }

            if (from != null) {
                builder.append("&from=").append(from);
            }

            if (to != null) {
                builder.append("&to=").append(to);
            }

            if (completed != null) {
                builder.append("&completed=").append(completed);
            }

            link = builder.toString();
        }
        return link;
    }
}