package com.example.gasip.professor.model;

import com.example.gasip.major.model.Major;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "prof")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "교수 관련 VO")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "교수 ID")
    private Long profId;
    @Column(nullable = false,length = 40)
    @Schema(description = "교수 이름")
    private String profName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_ID")
    @Schema(description = "교수 전공")
    private Major major;

    @Builder
    public Professor(Long profId, String profName,Major major) {
        this.profId = profId;
        this.profName = profName;
        this.major = major;
    }

}



