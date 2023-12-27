package com.example.gasip.professordetail.model;

import com.example.gasip.major.model.Major;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "prof")
@Schema(description = "교수 세부정보 관련 VO")
public class ProfessorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "교수 ID")
    private Long profId;

    @Column(nullable = false,length = 40)
    @Schema(description = "교수 이름")
    private String profName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_ID")
    @Schema(description = "교수 전공 ID")
    private Major major;

    @Builder
    public ProfessorDetail(Long profId, String profName, Major major) {
        this.profId = profId;
        this.profName = profName;
        this.major = major;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfessorDetail that)) return false;
        return profId != null && profId.equals(that.getProfId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(profId);
    }
}
