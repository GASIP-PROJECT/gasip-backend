package com.example.gasip.major.model;

import com.example.gasip.college.model.College;
import com.example.gasip.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "major")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Major extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long majorId;
    @Column(nullable = false, name = "name")
    private String majorName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_ID")
    private College college;

}
