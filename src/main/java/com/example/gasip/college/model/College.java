package com.example.gasip.college.model;

import com.example.gasip.common.BaseTimeEntity;
import com.example.gasip.professor.model.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "college")
@Schema(description = "단과대 관련 VO")
@SuperBuilder
public class College extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "단과대 ID")
    private Long collegeId;
    @Column(nullable = false)
    @Schema(description = "단과대 이름")
    private String collegeName;

    public College(Long collegeId, LocalDateTime regDate, LocalDateTime updateDate, String collegeName) {
        super(regDate, updateDate);
        this.collegeId = collegeId;
        this.collegeName = collegeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof College that)) return false;
        return collegeId != null && collegeId.equals(that.getCollegeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(collegeId);
    }
}
