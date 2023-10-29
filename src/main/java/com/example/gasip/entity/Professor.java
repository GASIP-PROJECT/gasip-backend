package com.example.gasip.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@Table(name = "prof")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profId;

    @Column(nullable = false,length = 40)
    private String profName;

    @Column(nullable = false)
    private Long majorId;

    @Column(nullable = false,length = 100)
    private String majorName;

    @Builder
    public Professor(Long profId, String profName, Long majorId, String majorName) {
        this.profId = profId;
        this.profName = profName;
        this.majorId = majorId;
        this.majorName = majorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor that)) return false;
        return profId != null && profId.equals(that.getProfId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(profId);
    }

}



