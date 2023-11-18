package com.example.gasip.major.model;

import com.example.gasip.college.model.College;
import com.example.gasip.common.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "major")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "전공 관련 VO")
public class Major extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "전공 ID")
    private Long majorId;
    @Column(nullable = false, name = "name")
    @Schema(description = "전공 이름")
    private String majorName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_ID")
    @Schema(description = "전공 단과대")
    private College college;

}
