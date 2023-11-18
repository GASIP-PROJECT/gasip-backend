package com.example.gasip.professordetail.model;

import com.example.gasip.major.model.Major;
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
public class ProfessorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profId;

    @Column(nullable = false,length = 40)
    private String profName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
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
