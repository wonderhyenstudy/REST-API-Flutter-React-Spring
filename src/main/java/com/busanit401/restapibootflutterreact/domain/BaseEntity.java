package com.busanit401.restapibootflutterreact.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity { // 설계 클래스 목적으로 사용 할 예정

    // 생성 시간 필드
    @CreatedDate
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate;

    // 수정 시간 필드
    // 주의, 수정할 때, 자동으로 시간이 기록이 되는게 목적인데, 옵션에서 수정을 못하게 막으면, 모순. !!!
    // updatable = false , 제외 했음.
    @LastModifiedDate
    @Column(name = "modDate")
    private LocalDateTime modDate;
}