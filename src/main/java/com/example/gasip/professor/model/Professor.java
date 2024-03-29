package com.example.gasip.professor.model;

import com.example.gasip.category.model.Category;
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
    @Column(nullable = false, length = 40)
    @Schema(description = "교수 이름")
    private String profName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Category category;

    @Transient
    private String averageGradePoint;


    @Builder
    public Professor(Long profId, String profName, Category category) {
        this.profId = profId;
        this.profName = profName;
        this.category = category;
    }

    public void updateProfessor(String averagePoint) {
        this.averageGradePoint = averagePoint;
    }

}



