package com.example.gasip.Board.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스가 BaseTimeEntity를 상속할 경우 필드를 Column으로 인식하게 함
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능을 포함시킴
                                               // JPA에서 시간에 대한 값을 자동으로 넣어줌
public class BaseTimeEntity {

    @CreatedDate // Entity 생성 시 시간 저장
    private LocalDateTime regDate;

    @LastModifiedDate // 조회한 Entity 값 변경 시 시간 저장
    private LocalDateTime updateDate;
}
