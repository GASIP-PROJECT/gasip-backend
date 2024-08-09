package com.example.gasip.professor.model;

import com.example.gasip.category.model.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

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
    @Schema(description = "직위/직책")
    private String position;
    @Schema(description = "학위전공")
    private String degreeMajor;
    @Schema(description = "전화번호")
    private String tel;
    @Schema(description = "사무실 위치")
    private String location;
    @Schema(description = "학력")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> education;
    @Transient
    private String averageGradePoint;
    @Transient
    private Boolean isGrade;


    @Builder
    public Professor(Long profId, String profName, Category category) {
        this.profId = profId;
        this.profName = profName;
        this.category = category;
    }

    public void updateProfessor(String averagePoint) {
        this.averageGradePoint = averagePoint;
    }

    public void updateGrade(Boolean isGrade) {
        this.isGrade=isGrade;
    }
    public void updatePostion(String position) {
        this.position=position;
    }
    public void updateDegreeMajor(String degreeMajor) {
        this.degreeMajor=degreeMajor;
    }
    public void updateTel(String tel) {
        this.tel=tel;
    }
    public void updateLocation(String location) {
        this.location=location;
    }
    public void updateEducation(List<String> education) {
        this.education=education;
    }

}



