package com.example.gasip.global.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스가 BaseTimeEntity를 상속할 경우 필드를 Column으로 인식하게 함
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능을 포함시킴
// JPA에서 시간에 대한 값을 자동으로 넣어줌
@SuperBuilder
@NoArgsConstructor
@Schema(description = "시간 관련 VO")
public abstract class BaseTimeEntity {

    @CreatedDate // Entity 생성 시 시간 저장
    @Column(updatable = false, name = "reg_date")
    @Schema(description = "작성 시간")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime regDate;

    @LastModifiedDate // 조회한 Entity 값 변경 시 시간 저장
    @Column(name = "update_date")
    @Schema(description = "수정 시간")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime updateDate;

    public BaseTimeEntity(LocalDateTime regDate, LocalDateTime updateDate) {
        this.regDate = regDate;
        this.updateDate = updateDate;
    }

}
