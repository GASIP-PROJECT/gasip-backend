package com.example.gasip.college.model;

import com.example.gasip.global.entity.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Schema(description = "단과대 관련 VO")
public class College extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "단과대 ID")
    private Long collegeId;
    @Column(nullable = false)
    @Schema(description = "단과대 이름")
    private String collegeName;
}
