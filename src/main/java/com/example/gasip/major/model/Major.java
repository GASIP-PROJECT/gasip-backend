package com.example.gasip.major.model;

import com.example.gasip.college.model.College;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "major")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Schema(description = "전공 관련 VO")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "전공 ID")
    private Long majorId;
    @Column(nullable = false, name = "name")
    @Schema(description = "전공 이름")
    private String majorName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_ID")
    @Schema(description = "전공 단과대")
    private College college;

}
